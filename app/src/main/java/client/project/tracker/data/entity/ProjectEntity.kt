package client.project.tracker.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import client.project.tracker.domain.Priority
import client.project.tracker.domain.ProjectStatus
import java.time.LocalDate

@Entity(tableName = "projects")
data class ProjectEntity (

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val clientName: String,
    val projectName: String,
    val description: String,
    val status: ProjectStatus,
    val priority: Priority,
    val startDate: String,
    val dueDate: String
)