package contact.info.harry.presentation.main

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import contact.info.harry.domain.model.Student
import contact.info.harry.domain.use_case.UseCases
import contact.info.harry.presentation.utils.Routes
import contact.info.harry.presentation.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var student by mutableStateOf<Student?>(null)
        private set

    var name by mutableStateOf("")
        private set

    var school by mutableStateOf("")
        private set

    var year by mutableStateOf("")
        private set

    var department by mutableStateOf("")
        private set

    var popDialog by mutableStateOf(false)
        private set

    var details by mutableStateOf("")
        private set

    var numBadges by mutableStateOf(0)
        private set

    private val _radioGroupState = mutableStateOf(
        RadioGroupState(
            skillChecked = true
        )
    )
    val radioGroupState: State<RadioGroupState> = _radioGroupState

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var currentId: Int? = null

    init {
        val email = savedStateHandle.get<String>("email")!!
        if (email != "") {
            viewModelScope.launch {
                useCases.displayInfo(email).let { student ->
                    currentId = student.id
                    name = student.name
                    school = student.school
                    year = student.year
                    department = student.department
                    numBadges = student.numBadges
                    this@MainViewModel.student = student
                }
            }
        }
    }

    fun onEvent(event: MainScreenEvent) {
        when (event) {
            is MainScreenEvent.OnNameChange -> {
                name = event.name
            }
            is MainScreenEvent.OnSchoolChange -> {
                school = event.school
            }
            is MainScreenEvent.OnYearChange -> {
                year = event.year
            }
            is MainScreenEvent.OnDepartmentChange -> {
                department = event.department
            }
            is MainScreenEvent.OnAddBadgesClick -> {
                popDialog = !popDialog
                details = ""
                _radioGroupState.value = RadioGroupState(
                    skillChecked = true
                )
            }
            is MainScreenEvent.OnShareClick -> {
                viewModelScope.launch {
                    try {
                        useCases.createUser(
                            Student(
                                email = student!!.email,
                                password = student!!.password,
                                name = name,
                                school = school,
                                year = year,
                                department = department,
                                numBadges = numBadges,
                                id = currentId
                            )
                        )
                        sendUiEvent(
                            UiEvent.Navigate(
                                Routes.screenShare + "?email=${student!!.email}"
                            )
                        )
                    } catch (e: Exception) {
                        sendUiEvent(
                            UiEvent.ShowSnackBar(
                                message = e.message ?: "Something went wrong!"
                            )
                        )
                    }
                }
            }
            is MainScreenEvent.OnDetailsChange -> {
                details = event.details
            }
            is MainScreenEvent.OnSubmit -> {
                popDialog = !popDialog
                details = ""
            }
        }
    }

    fun onRadioClick(click: RadioGroupState) {
        _radioGroupState.value = click
    }

    fun increaseBadges() {
        viewModelScope.launch {
            numBadges += 1
            Log.i("Num", "$numBadges")
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}