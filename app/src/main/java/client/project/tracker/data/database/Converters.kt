package client.project.tracker.data.database

import androidx.room.TypeConverter
import client.project.tracker.domain.Priority
import client.project.tracker.domain.ProjectStatus

class Converters {
    @TypeConverter
    fun fromProjectStatus(status: ProjectStatus): String {
        return status.name
    }

    @TypeConverter
    fun toProjectStatus(value: String): ProjectStatus {
        return try {
            ProjectStatus.valueOf(value)
        } catch (e: IllegalArgumentException) {
            ProjectStatus.NOT_STARTED
        }
    }

    @TypeConverter
    fun fromPriority(priority: Priority): String {
        return priority.name
    }

    @TypeConverter
    fun toPriority(value: String): Priority {
        return try {
            Priority.valueOf(value)
        } catch (e: IllegalArgumentException) {
            Priority.MEDIUM
        }
    }
}