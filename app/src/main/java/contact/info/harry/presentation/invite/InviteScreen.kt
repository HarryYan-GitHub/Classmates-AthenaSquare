package contact.info.harry.presentation.invite

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import contact.info.harry.presentation.components.StudentItem
import contact.info.harry.presentation.utils.UiEvent
import kotlinx.coroutines.flow.collectLatest

@Composable
fun InviteScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: InviteViewModel = hiltViewModel()
) {

    val students = viewModel.classmatesState.value
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
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = "College - ${viewModel.school}",
                    fontSize = 18.sp,
                    color = Color.White,
                    modifier = Modifier
                        .padding(6.dp)
                )
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Year\n${viewModel.year}",
                        fontSize = 18.sp,
                        color = Color.White,
                        modifier = Modifier
                            .padding(6.dp)
                            .align(CenterVertically)
                            .weight(0.7f)
                    )
                    Spacer(modifier = Modifier.weight(0.2f))
                    Text(
                        text = "Department\n${viewModel.department}",
                        fontSize = 18.sp,
                        color = Color.White,
                        modifier = Modifier
                            .padding(6.dp)
                            .align(CenterVertically)
                            .weight(1f)
                    )
                }
                Spacer(modifier = Modifier.height(36.dp))
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = "Class",
                        fontSize = 23.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(4.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Box(
                        modifier = Modifier.weight(1.7f)
                    ) {
                        LazyColumn {
                            items(students.classmates.size) { i ->
                                val student = students.classmates[i]
                                if (student.email != viewModel.student?.email) {
                                    StudentItem(student = student)
                                }
                            }
                        }
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = CenterVertically
                        ) {
                            OutlinedTextField(
                                value = viewModel.emailInvite,
                                onValueChange = {
                                    viewModel.onEvent(InviteScreenEvent.OnInviteEmailChange(it))
                                },
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Email
                                ),
                                label = {
                                    Text(text = "Invite")
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(3f)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Button(
                                onClick = {
                                    viewModel.onEvent(InviteScreenEvent.OnGoClick)
                                },
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .weight(0.7f)
                            ) {
                                Text(text = "Go")
                            }
                        }
                    }
                }
            }
            Row(
                modifier = Modifier
                    .align(BottomCenter),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        viewModel.onEvent(InviteScreenEvent.OnReturnClick)
                    },
                    modifier = Modifier
                        .clip(CircleShape)
                        .padding(12.dp)
                        .weight(1f)
                ) {
                    Icon(Icons.Filled.Home, contentDescription = "Home")
                }
                Spacer(modifier = Modifier.width(4.dp))
                Button(
                    onClick = {
                        viewModel.onEvent(InviteScreenEvent.OnGoClick)
                    },
                    modifier = Modifier
                        .clip(CircleShape)
                        .padding(12.dp)
                        .weight(1f),
                    enabled = false
                ) {
                    Icon(Icons.Filled.Person, contentDescription = "Invite")
                }
            }
        }
    }
}