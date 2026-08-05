package client.project.tracker.presentation.addedit

import client.project.tracker.domain.Priority
import client.project.tracker.domain.ProjectStatus

sealed interface AddEditEvent {
    data class ClientNameChanged(val value: String) : AddEditEvent
    data class ProjectNameChanged(val value: String) : AddEditEvent
    data class DescriptionChanged(val value: String) : AddEditEvent
    data class StatusChanged(val value: ProjectStatus) : AddEditEvent
    data class PriorityChanged(val value: Priority) : AddEditEvent
    data class StartDateChanged(val value: String) : AddEditEvent
    data class DueDateChanged(val value: String) : AddEditEvent
    data object Save : AddEditEvent
}