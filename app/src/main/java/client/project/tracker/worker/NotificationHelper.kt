package client.project.tracker.worker
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import client.project.tracker.MainActivity
import client.project.tracker.R

class NotificationHelper(
    private val context: Context
) {

    companion object {

        const val CHANNEL_ID = "project_due"

    }

    fun createChannel() {

        val manager =
            context.getSystemService(NotificationManager::class.java)

        val channel = NotificationChannel(
            CHANNEL_ID,
            "Project Reminder",
            NotificationManager.IMPORTANCE_DEFAULT
        )

        manager.createNotificationChannel(channel)

    }

    fun showNotification(
        id: Int,
        title: String,
        message: String
    ) {

        val intent = Intent(
            context,
            MainActivity::class.java
        ).apply {

            putExtra("projectId", id)

        }

        val pendingIntent =
            PendingIntent.getActivity(
                context,
                id,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or
                        PendingIntent.FLAG_IMMUTABLE
            )

        val notification =
            NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(title)
                .setContentText(message)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build()

        val manager =
            context.getSystemService(NotificationManager::class.java)

        manager.notify(id, notification)

    }

}