package contact.info.harry.domain.repository

import contact.info.harry.domain.model.Student
import kotlinx.coroutines.flow.Flow

interface StudentRepository {

    suspend fun createStudent(student: Student)

    suspend fun displayInfo(email: String): Student

    fun getClassmates(): Flow<List<Student>>

    suspend fun getStudent(email: String): Student?
}