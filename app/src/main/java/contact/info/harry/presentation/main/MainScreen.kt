package contact.info.harry.presentation.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import contact.info.harry.presentation.components.BadgesDialog
import contact.info.harry.presentation.utils.UiEvent
import kotlinx.coroutines.flow.collectLatest

@Composable
fun MainScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {

    val numBadges = viewModel.numBadges
    val popDialog = viewModel.popDialog
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
                    .padding(16.dp),
                horizontalAlignment = Alignment.End
            ) {
                OutlinedTextField(
                    value = viewModel.name,
                    onValueChange = {
                        viewModel.onEvent(MainScreenEvent.OnNameChange(it))
                    },
                    label = {
                        Text(text = "Name")
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(24.dp))
                OutlinedTextField(
                    value = viewModel.school,
                    onValueChange = {
                        viewModel.onEvent(MainScreenEvent.OnSchoolChange(it))
                    },
                    label = {
                        Text(text = "College")
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OutlinedTextField(
                        value = viewModel.year,
                        onValueChange = { typedWords ->
                            viewModel.onEvent(MainScreenEvent.OnYearChange(typedWords.filter { it.isDigit() }))
                        },
                        placeholder = {
                            Text(text = "eg. 2019")
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        label = {
                            Text(text = "Year")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )
                    Spacer(modifier = Modifier.weight(0.05f))
                    OutlinedTextField(
                        value = viewModel.department,
                        onValueChange = {
                            viewModel.onEvent(MainScreenEvent.OnDepartmentChange(it))
                        },
                        label = {
                            Text(text = "Department")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row {
                        RadioButton(selected = numBadges > 0, onClick = { })
                        Spacer(modifier = Modifier.width(2.dp))
                        RadioButton(selected = numBadges > 1, onClick = { })
                        Spacer(modifier = Modifier.width(2.dp))
                        RadioButton(selected = numBadges > 2, onClick = { })
                        Spacer(modifier = Modifier.width(2.dp))
                        RadioButton(selected = numBadges > 3, onClick = { })
                        Spacer(modifier = Modifier.width(2.dp))
                        RadioButton(selected = numBadges > 4, onClick = { })
                        Spacer(modifier = Modifier.width(2.dp))
                    }
                    Button(
                        onClick = {
                            viewModel.onEvent(MainScreenEvent.OnAddBadgesClick)
                        },
                        enabled = numBadges < 5
                    ) {
                        Text(text = "Add Badges")
                    }
                    if (popDialog) {
                        BadgesDialog()
                    }
                }
            }
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {},
                    modifier = Modifier
                        .clip(CircleShape)
                        .padding(12.dp)
                        .weight(1f),
                    enabled = false
                ) {
                    Icon(Icons.Filled.Home, contentDescription = "Home")
                }
                Spacer(modifier = Modifier.width(4.dp))
                Button(
                    onClick = {
                        viewModel.onEvent(MainScreenEvent.OnShareClick)
                    },
                    modifier = Modifier
                        .clip(CircleShape)
                        .padding(12.dp)
                        .weight(1f)
                ) {
                    Icon(Icons.Filled.Person, contentDescription = "Invite")
                }
            }
        }
    }
}