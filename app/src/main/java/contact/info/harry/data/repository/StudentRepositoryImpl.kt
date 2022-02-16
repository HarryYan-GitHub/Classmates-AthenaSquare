package contact.info.harry.data.repository

import contact.info.harry.data.datasource.StudentDao
import contact.info.harry.domain.model.Student
import contact.info.harry.domain.repository.StudentRepository
import kotlinx.coroutines.flow.Flow

class StudentRepositoryImpl(
    private val dao: StudentDao
): StudentRepository {

    override suspend fun createStudent(student: Student) {
        dao.createStudent(student)
    }

    override suspend fun displayInfo(email: String): Student {
        return dao.displayInfo(email)
    }

    override fun getClassmates(): Flow<List<Student>> {
        return dao.getClassmates()
    }

    override suspend fun getStudent(email: String): Student? {
        return dao.getStudent(email)
    }
}