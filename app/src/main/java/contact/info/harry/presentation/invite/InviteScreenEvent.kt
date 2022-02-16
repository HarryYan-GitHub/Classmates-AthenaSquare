package contact.info.harry.presentation.invite

sealed class InviteScreenEvent {
    data class OnInviteEmailChange(val email: String): InviteScreenEvent()
    object OnReturnClick: InviteScreenEvent()
    object OnGoClick: InviteScreenEvent()
}
