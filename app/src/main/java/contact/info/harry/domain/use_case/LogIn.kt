package contact.info.harry.domain.use_case

import contact.info.harry.domain.model.InvalidLogInException
import contact.info.harry.domain.model.Student
import contact.info.harry.domain.repository.StudentRepository

class LogIn(
    private val repository: StudentRepository
) {

    @Throws(InvalidLogInException::class)
    suspend operator fun invoke(email: String): Student {
        repository.getStudent(email) ?: throw InvalidLogInException("User doesn't exist.")
        return repository.displayInfo(email)
    }
}