package contact.info.harry.presentation.main

sealed class MainScreenEvent {
    data class OnNameChange(val name: String): MainScreenEvent()
    data class OnSchoolChange(val school: String): MainScreenEvent()
    data class OnYearChange(val year: String): MainScreenEvent()
    data class OnDepartmentChange(val department: String): MainScreenEvent()
    object OnAddBadgesClick: MainScreenEvent()
    object OnShareClick: MainScreenEvent()
    data class OnDetailsChange(val details: String): MainScreenEvent()
    object OnSubmit: MainScreenEvent()
}
