package contact.info.harry.presentation.intro

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import contact.info.harry.presentation.utils.UiEvent
import kotlinx.coroutines.flow.collectLatest

@Composable
fun IntroScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: IntroViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                is UiEvent.Navigate -> {
                    onNavigate(event)
                }
                else -> Unit
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        Text(
            text = "Classmates",
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.
                    align(Alignment.Center)
        )
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    viewModel.onEvent(IntroScreenEvent.OnLogInClick)
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Log In",
                    fontSize = 25.sp
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    viewModel.onEvent(IntroScreenEvent.OnSignUpClick)
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Sign Up",
                    fontSize = 25.sp
                )
            }
        }
    }
}