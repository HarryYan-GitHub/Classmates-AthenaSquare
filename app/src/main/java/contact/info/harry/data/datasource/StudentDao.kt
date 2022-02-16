package contact.info.harry.data.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import contact.info.harry.domain.model.Student
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createStudent(student: Student)

    @Query("SELECT * FROM student WHERE email = :email")
    suspend fun displayInfo(email: String): Student

    @Query("SELECT * FROM student")
    fun getClassmates(): Flow<List<Student>>

    @Query("SELECT * FROM student WHERE email = :email")
    suspend fun getStudent(email: String): Student?
}