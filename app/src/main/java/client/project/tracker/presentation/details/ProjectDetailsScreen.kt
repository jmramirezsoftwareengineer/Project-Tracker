package client.project.tracker.presentation.details

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectDetailsScreen(
    projectId: Long,
    onBack: () -> Unit,
    onEdit: (Long) -> Unit,
    viewModel: ProjectDetailsViewModel = koinViewModel()
) {

    val project by viewModel.project.collectAsStateWithLifecycle()

    LaunchedEffect(projectId) {
        viewModel.load(projectId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Project Details")
                }
            )
        },
        floatingActionButton = {
            Row {
                FloatingActionButton(
                    onClick = {
                        onEdit(projectId)
                    }
                ) {
                    Icon(Icons.Default.Edit, null)
                }

                Spacer(modifier = Modifier.width(16.dp))

                FloatingActionButton(
                    onClick = {
                        viewModel.delete()
                        onBack()
                    }
                ) {

                    Icon(Icons.Default.Delete, null)

                }

            }

        }

    ) { padding ->

        project?.let {

            Column(

                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)

            ) {

                Text(
                    it.projectName,
                    style = MaterialTheme.typography.headlineSmall
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text("Client: ${it.clientName}")

                Spacer(modifier = Modifier.height(8.dp))

                Text(it.description)

                Spacer(modifier = Modifier.height(16.dp))

                Text("Status: ${it.status}")

                Text("Priority: ${it.priority}")

                Spacer(modifier = Modifier.height(8.dp))

                Text("Start: ${it.startDate}")

                Text("Due: ${it.dueDate}")

            }

        }

    }

}