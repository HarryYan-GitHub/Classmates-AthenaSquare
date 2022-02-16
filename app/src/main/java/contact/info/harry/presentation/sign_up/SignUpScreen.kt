package contact.info.harry.presentation.sign_up

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import contact.info.harry.presentation.utils.UiEvent
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SignUpScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: SignUpViewModel = hiltViewModel()
) {

    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                is UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is UiEvent.Navigate -> {
                    onNavigate(event)
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.End
        ) {
            TextField(
                value = viewModel.email,
                onValueChange = {
                    viewModel.onEvent(SignUpScreenEvent.OnEmailChange(it))
                },
                placeholder = {
                    Text(text = "Phone or Email")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = viewModel.password,
                onValueChange = {
                    viewModel.onEvent(SignUpScreenEvent.OnPasswordChange(it))
                },
                placeholder = {
                    Text(text = "Password")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    viewModel.onEvent(SignUpScreenEvent.OnSignUpClick)
                }) {
                Text(text = "Sign Up")
            }
        }
    }
}