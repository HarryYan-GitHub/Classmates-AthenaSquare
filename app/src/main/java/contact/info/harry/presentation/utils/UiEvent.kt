package contact.info.harry.presentation.utils

sealed class UiEvent {
    data class ShowSnackBar(val message: String): UiEvent()
    data class Navigate(val route: String): UiEvent()
}
