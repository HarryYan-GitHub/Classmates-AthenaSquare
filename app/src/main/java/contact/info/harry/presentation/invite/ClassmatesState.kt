package contact.info.harry.presentation.invite

import contact.info.harry.domain.model.Student

data class ClassmatesState(
    val classmates: List<Student> = emptyList()
)
