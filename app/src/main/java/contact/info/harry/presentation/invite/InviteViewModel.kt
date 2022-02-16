package contact.info.harry.presentation.invite

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
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InviteViewModel @Inject constructor(
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var student by mutableStateOf<Student?>(null)
        private set

    var school by mutableStateOf("")
        private set

    var year by mutableStateOf("")
        private set

    var department by mutableStateOf("")
        private set

    var emailInvite by mutableStateOf("")
        private set

    private val _classmatesState = mutableStateOf(ClassmatesState())
    val classmatesState = _classmatesState

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        val email = savedStateHandle.get<String>("email")!!
        if (email != "") {
            viewModelScope.launch {
                useCases.displayInfo(email).let { student ->
                    school = student.school
                    year = student.year
                    department = student.department
                    this@InviteViewModel.student = student
                }
            }
        }
        getClassmates()
    }

    fun onEvent(event: InviteScreenEvent) {
        when (event) {
            is InviteScreenEvent.OnInviteEmailChange -> {
                emailInvite = event.email
            }
            is InviteScreenEvent.OnReturnClick -> {
                viewModelScope.launch {
                    sendUiEvent(
                        UiEvent.Navigate(
                            Routes.screenMain + "?email=${student!!.email}"
                        )
                    )
                }
            }
            is InviteScreenEvent.OnGoClick -> {
                viewModelScope.launch {
                    sendUiEvent(
                        UiEvent.ShowSnackBar(
                            message = "Invitation is sent successfully!"
                        )
                    )
                }
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

    private fun getClassmates() {
        useCases.getClassmates().onEach { students ->
            _classmatesState.value = classmatesState.value.copy(
                classmates = students
            )
        }.launchIn(viewModelScope)
    }
}