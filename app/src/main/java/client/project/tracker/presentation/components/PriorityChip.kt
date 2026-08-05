package client.project.tracker.presentation.components

import androidx.compose.material3.AssistChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import client.project.tracker.domain.Priority

@Composable
fun PriorityChip(
    priority: Priority
) {

    AssistChip(
        onClick = { },
        label = {
            Text(priority.name)
        }
    )

}