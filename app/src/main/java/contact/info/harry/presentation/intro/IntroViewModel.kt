package contact.info.harry.presentation.intro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import contact.info.harry.presentation.utils.Routes
import contact.info.harry.presentation.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IntroViewModel @Inject constructor(): ViewModel() {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: IntroScreenEvent) {
        when (event) {
            is IntroScreenEvent.OnLogInClick -> {
                sendUiEvent(
                    UiEvent.Navigate(Routes.screenLogIn)
                )
            }
            is IntroScreenEvent.OnSignUpClick -> {
                sendUiEvent(
                    UiEvent.Navigate(Routes.screenSignUp)
                )
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}