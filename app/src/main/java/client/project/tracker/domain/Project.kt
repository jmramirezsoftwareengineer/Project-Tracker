package client.project.tracker.domain

import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class Project (
    val id: Long = 0,
    val clientName: String,
    val projectName: String,
    val description: String,
    val status: ProjectStatus,
    val priority: Priority,
    val startDate: String,
    val dueDate: String
)

enum class ProjectStatus {
    @SerializedName("Not Started")
    NOT_STARTED,
    @SerializedName("In Progress")
    IN_PROGRESS,
    @SerializedName("On Hold")
    ON_HOLD,
    @SerializedName("Completed")
    COMPLETED,
    @SerializedName("Cancelled")
    CANCELLED,
    @SerializedName("Planning")
    PLANNING
}

enum class Priority {
    @SerializedName("Low")
    LOW,
    @SerializedName("Medium")
    MEDIUM,
    @SerializedName("High")
    HIGH,
    @SerializedName("Urgent")
    URGENT
}