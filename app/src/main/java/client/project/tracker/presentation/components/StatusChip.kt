package client.project.tracker.presentation.components

import androidx.compose.material3.AssistChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import client.project.tracker.domain.ProjectStatus

@Composable
fun StatusChip(
    status: ProjectStatus
) {

    AssistChip(
        onClick = { },
        label = {
            Text(status.name.replace("_", " "))
        }
    )

}