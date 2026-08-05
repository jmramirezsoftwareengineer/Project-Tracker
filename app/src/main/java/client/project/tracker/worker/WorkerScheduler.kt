package client.project.tracker.worker

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

object WorkerScheduler {

    fun schedule(context: Context) {

        val request = PeriodicWorkRequestBuilder<
                DueProjectNotificationWorker
                >(
            1,
            TimeUnit.DAYS
        ).build()

        WorkManager
            .getInstance(context)
            .enqueueUniquePeriodicWork(
                "due_project_worker",
                ExistingPeriodicWorkPolicy.UPDATE,
                request
            )
    }
}