package client.project.tracker.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import client.project.tracker.data.database.ProjectDatabase
import client.project.tracker.presentation.settings.SettingsDataStore
import kotlinx.coroutines.flow.first
import java.time.LocalDate

class DueProjectNotificationWorker(
    context: Context,
    params: WorkerParameters,
    private val database: ProjectDatabase,
    private val settings: SettingsDataStore
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {

        val config = settings.settings.first()

        if (!config.notificationsEnabled) {
            return Result.success()
        }

        val today = LocalDate.now()

        val dueDate = today.plusDays(
            config.reminderDays.toLong()
        )

        val projects =
            database.projectDao().getProjectsDueBetween(
                today.toString(),
                dueDate.toString()
            )

        val helper =
            NotificationHelper(applicationContext)
        helper.createChannel()
        projects.forEach {
            helper.showNotification(
                id = it.id.toInt(),
                title = it.projectName,
                message = "Due on ${it.dueDate}"
            )
        }

        return Result.success()
    }
}