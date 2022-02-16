package contact.info.harry.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import contact.info.harry.presentation.intro.IntroScreen
import contact.info.harry.presentation.invite.InviteScreen
import contact.info.harry.presentation.log_in.LogInScreen
import contact.info.harry.presentation.main.MainScreen
import contact.info.harry.presentation.sign_up.SignUpScreen
import contact.info.harry.presentation.utils.Routes
import contact.info.harry.ui.theme.ProjectTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjectTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Routes.screenIntro
                ) {
                    composable(
                        Routes.screenIntro
                    ) {
                        IntroScreen(onNavigate = {
                            navController.navigate(it.route)
                        })
                    }
                    composable(
                        Routes.screenSignUp
                    ) {
                        SignUpScreen(onNavigate = {
                            navController.navigate(it.route) {
                                popUpTo(Routes.screenIntro)
                            }
                        })
                    }
                    composable(
                        Routes.screenLogIn
                    ) {
                        LogInScreen(onNavigate = {
                            navController.navigate(it.route) {
                                popUpTo(Routes.screenIntro)
                            }
                        })
                    }
                    composable(
                        Routes.screenMain + "?email={email}",
                        arguments = listOf(
                            navArgument(name = "email") {
                                type = NavType.StringType
                                defaultValue = ""
                            }
                        )
                    ) {
                        MainScreen(onNavigate = {
                            navController.navigate(it.route)
                        })
                    }
                    composable(
                        Routes.screenShare + "?email={email}",
                        arguments = listOf(
                            navArgument(name = "email") {
                                type = NavType.StringType
                                defaultValue = ""
                            }
                        )
                    ) {
                        InviteScreen(onNavigate = {
                            navController.navigate(it.route) {
                                popUpTo(Routes.screenIntro)
                            }
                        })
                    }
                }
            }
        }
    }
}