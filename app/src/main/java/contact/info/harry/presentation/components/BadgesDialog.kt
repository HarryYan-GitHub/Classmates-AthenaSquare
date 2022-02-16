package contact.info.harry.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import contact.info.harry.presentation.main.MainScreenEvent
import contact.info.harry.presentation.main.MainViewModel
import contact.info.harry.presentation.main.RadioGroupState

@Composable
fun BadgesDialog(
    viewModel: MainViewModel = hiltViewModel()
) {
    val state = viewModel.radioGroupState.value
    AlertDialog(
        onDismissRequest = {
            viewModel.onEvent(MainScreenEvent.OnAddBadgesClick)
        },
        title = {
            Text(
                text = "Create a badge",
                textAlign = TextAlign.Center
            )
        },
        backgroundColor = Color.DarkGray,
        text = {
            Column {
                Row {
                    RadioButton(
                        selected = state.skillChecked,
                        onClick = {
                            viewModel.onRadioClick(
                                click = RadioGroupState(
                                    skillChecked = true,
                                    awardChecked = false,
                                    jobHeldChecked = false
                                )
                            )
                        },
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "skills",
                        fontSize = 15.sp
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    RadioButton(
                        selected = state.awardChecked,
                        onClick = {
                            viewModel.onRadioClick(
                                click = RadioGroupState(
                                    skillChecked = false,
                                    awardChecked = true,
                                    jobHeldChecked = false
                                )
                            )
                        },
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "awards",
                        fontSize = 15.sp
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    RadioButton(
                        selected = state.jobHeldChecked,
                        onClick = {
                            viewModel.onRadioClick(
                                click = RadioGroupState(
                                    skillChecked = false,
                                    awardChecked = false,
                                    jobHeldChecked = true
                                )
                            )
                        },
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "jobs held",
                        fontSize = 15.sp
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                Text(text = "Add details", fontSize = 20.sp)
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = viewModel.details,
                    onValueChange = {
                        viewModel.onEvent(MainScreenEvent.OnDetailsChange(it))
                    })
            }
        },
        confirmButton = {
            Button(
                modifier = Modifier.padding(8.dp),
                onClick = {
                    viewModel.increaseBadges()
                    viewModel.onEvent(MainScreenEvent.OnAddBadgesClick)
                })
            {
                Text(text = "Submit")
            }
        }
    )
}