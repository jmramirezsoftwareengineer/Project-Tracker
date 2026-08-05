package client.project.tracker.presentation.addedit

import client.project.tracker.domain.Priority
import client.project.tracker.domain.ProjectStatus
import java.time.LocalDate

data class AddEditUiState(

    val id: Long = 0,

    val clientName: String = "",

    val projectName: String = "",

    val description: String = "",

    val status: ProjectStatus = ProjectStatus.NOT_STARTED,

    val priority: Priority = Priority.MEDIUM,

    val startDate: String = LocalDate.now().toString(),

    val dueDate: String = LocalDate.now().toString(),

    val isSaving: Boolean = false,

    val error: String? = null

)