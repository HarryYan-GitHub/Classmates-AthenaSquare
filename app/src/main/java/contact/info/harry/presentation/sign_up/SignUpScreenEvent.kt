package contact.info.harry.presentation.sign_up

sealed class SignUpScreenEvent {
    data class OnEmailChange(val email: String): SignUpScreenEvent()
    data class OnPasswordChange(val password: String): SignUpScreenEvent()
    object OnSignUpClick: SignUpScreenEvent()
}
