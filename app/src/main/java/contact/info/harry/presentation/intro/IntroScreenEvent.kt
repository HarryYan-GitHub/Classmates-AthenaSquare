package contact.info.harry.presentation.intro

sealed class IntroScreenEvent {
    object OnLogInClick: IntroScreenEvent()
    object OnSignUpClick: IntroScreenEvent()
}
