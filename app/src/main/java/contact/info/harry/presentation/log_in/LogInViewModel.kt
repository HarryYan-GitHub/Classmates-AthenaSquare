package contact.info.harry.presentation.log_in

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import contact.info.harry.domain.use_case.UseCases
import contact.info.harry.presentation.utils.Routes
import contact.info.harry.presentation.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: LogInScreenEvent) {
        when (event) {
            is LogInScreenEvent.OnEmailChange -> {
                email = event.email
            }
            is LogInScreenEvent.OnPasswordChange -> {
                password = event.password
            }
            is LogInScreenEvent.OnLogInClick -> {
                viewModelScope.launch {
                    try {
                        useCases.logIn(email)
                        sendUiEvent(
                            UiEvent.Navigate(
                                Routes.screenMain + "?email=$email"
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
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}