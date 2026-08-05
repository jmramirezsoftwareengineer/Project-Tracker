package client.project.tracker.data.dto

import client.project.tracker.domain.Priority
import client.project.tracker.domain.ProjectStatus

data class ProjectDto(

    val id: Long,

    val clientName: String,

    val projectName: String,

    val description: String,

    val status: ProjectStatus,

    val priority: Priority,

    val startDate: String,

    val dueDate: String

)