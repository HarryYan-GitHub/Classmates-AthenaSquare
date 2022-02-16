package contact.info.harry.domain.use_case

import contact.info.harry.domain.model.InvalidAccountException
import contact.info.harry.domain.model.Student
import contact.info.harry.domain.repository.StudentRepository

class CreateUser(
    private val repository: StudentRepository
) {

    @Throws(InvalidAccountException::class)
    suspend operator fun invoke(student: Student) {
        if (student.email.isBlank()) {
            throw InvalidAccountException("The email can't be empty.")
        }
        if (student.password.isBlank()) {
            throw InvalidAccountException("The password can't be empty.")
        }
        repository.createStudent(student)
    }
}