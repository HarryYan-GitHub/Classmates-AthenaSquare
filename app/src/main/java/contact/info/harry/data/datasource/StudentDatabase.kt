package contact.info.harry.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import contact.info.harry.domain.model.Student

@Database(
    entities = [Student::class],
    version = 1
)
abstract class StudentDatabase: RoomDatabase() {

    abstract val dao: StudentDao

    companion object {
        const val DATABASE_NAME = "students_db"
    }
}