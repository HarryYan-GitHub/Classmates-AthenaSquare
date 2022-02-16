package contact.info.harry.domain.use_case

import contact.info.harry.domain.model.Student
import contact.info.harry.domain.repository.StudentRepository
import kotlinx.coroutines.flow.Flow

class GetClassmates(
    private val repository: StudentRepository
) {

    operator fun invoke(): Flow<List<Student>> {
        return repository.getClassmates()
    }
}