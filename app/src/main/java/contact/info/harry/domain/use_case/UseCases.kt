package contact.info.harry.domain.use_case

data class UseCases(
    val createUser: CreateUser,
    val displayInfo: DisplayInfo,
    val getClassmates: GetClassmates,
    val logIn: LogIn
)
