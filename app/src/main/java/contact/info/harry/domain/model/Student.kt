package contact.info.harry.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Student(
    val email: String,
    val password: String,
    val name: String = "",
    val school: String = "",
    val year: String = "",
    val department: String = "",
    val numBadges: Int = 0,
    @PrimaryKey val id: Int? = null
)

class InvalidAccountException(message: String): Exception(message)

class InvalidLogInException(message: String): Exception(message)