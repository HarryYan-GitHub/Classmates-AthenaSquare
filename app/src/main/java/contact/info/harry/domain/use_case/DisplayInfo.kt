package contact.info.harry.domain.use_case

import contact.info.harry.domain.model.Student
import contact.info.harry.domain.repository.StudentRepository

class DisplayInfo(
    private val repository: StudentRepository
) {

    suspend operator fun invoke(email: String): Student {
        return repository.displayInfo(email)
    }
}