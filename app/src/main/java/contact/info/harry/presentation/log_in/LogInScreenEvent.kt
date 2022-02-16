package contact.info.harry.presentation.log_in

sealed class LogInScreenEvent {
    data class OnEmailChange(val email: String): LogInScreenEvent()
    data class OnPasswordChange(val password: String): LogInScreenEvent()
    object OnLogInClick: LogInScreenEvent()
}
