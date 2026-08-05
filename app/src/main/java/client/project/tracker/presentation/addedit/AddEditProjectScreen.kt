package client.project.tracker.presentation.addedit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import client.project.tracker.domain.ProjectStatus
import client.project.tracker.presentation.components.DatePickerField
import client.project.tracker.presentation.components.PriorityDropdown
import client.project.tracker.presentation.components.StatusDropdown
import org.koin.compose.viewmodel.koinViewModel
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditProjectScreen(
    projectId: Long,
    onBack: () -> Unit,
    viewModel: AddEditProjectViewModel = koinViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(projectId) {
        viewModel.load(projectId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        if (state.id == 0L)
                            "Add Project"
                        else
                            "Edit Project"
                    )
                }
            )
        }

    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),

            verticalArrangement = Arrangement.spacedBy(12.dp)

        ) {

            // Form fields go here
            OutlinedTextField(
                value = state.clientName,
                onValueChange = {
                    viewModel.onEvent(
                        AddEditEvent.ClientNameChanged(it)
                    )
                },
                label = {
                    Text("Client Name")
                },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = state.projectName,
                onValueChange = {
                    viewModel.onEvent(
                        AddEditEvent.ProjectNameChanged(it)
                    )
                },
                label = {
                    Text("Project Name")
                },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = state.description,
                onValueChange = {
                    viewModel.onEvent(
                        AddEditEvent.DescriptionChanged(it)
                    )
                },
                label = {
                    Text("Description")
                },
                minLines = 4,
                modifier = Modifier.fillMaxWidth()
            )

            StatusDropdown(
                status = state.status,
                onStatusSelected = {
                    viewModel.onEvent(
                        AddEditEvent.StatusChanged(it)
                    )
                }
            )

            PriorityDropdown(
                priority = state.priority,
                onPrioritySelected = {
                    viewModel.onEvent(
                        AddEditEvent.PriorityChanged(it)
                    )
                }
            )

            DatePickerField(
                label = "Start Date",
                value = state.startDate,
                onDateSelected = {
                    viewModel.onEvent(
                        AddEditEvent.StartDateChanged(it)
                    )
                }
            )

            DatePickerField(
                label = "Due Date",
                value = state.dueDate,
                onDateSelected = {
                    viewModel.onEvent(
                        AddEditEvent.DueDateChanged(it)
                    )
                }
            )

            Button(
                onClick = {
                    viewModel.onEvent(AddEditEvent.Save)
                    onBack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    if (state.id == 0L)
                        "Save Project"
                    else
                        "Update Project"

                )
            }

            state.error?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }

    }

}