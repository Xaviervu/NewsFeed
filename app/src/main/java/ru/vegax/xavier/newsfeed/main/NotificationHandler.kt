package ru.vegax.xavier.newsfeed.main

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import ru.vegax.xavier.newsfeed.R


class NotificationHandler(private val mContext: Context) {
    private val TAG = "XavvNotificationHandler"
    private val ACTION_UPDATE_NOTIFICATION = "ru.vegax.xavier.newsfeed.main.ACTION_UPDATE_NOTIFICATION"
    private val PRIMARY_CHANNEL_ID = "primary_notification_channel"
    val NEWS_ID = "ru.vegax.xavier.newsfeed.main.NEWS_ID"

    private lateinit var mNotifyManager: NotificationManager

    init {
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        mNotifyManager = mContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                PRIMARY_CHANNEL_ID,
                mContext.getString(R.string.notification_channel_name),
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.description = mContext.getString(R.string.notification_channel_description)

            mNotifyManager.createNotificationChannel(notificationChannel)
        }
    }

    fun sendNotification(id: Int, newsId: String, title: String, content: String) {
        val notifyBuilder = getNotificationBuilder(id, newsId, title, content)
        mNotifyManager.notify(id, notifyBuilder.build())
    }

    private fun getNotificationBuilder(
        id: Int,
        newsId: String,
        title: String,
        content: String
    ): NotificationCompat.Builder {
        val notificationIntent = Intent(this.mContext, MainActivity::class.java)
        notificationIntent.putExtra(NEWS_ID, newsId)
        notificationIntent.action = ACTION_UPDATE_NOTIFICATION
        val notificationPendingIntent = PendingIntent.getActivity(
            this.mContext, id, notificationIntent,
            PendingIntent.FLAG_ONE_SHOT
        )

        return NotificationCompat.Builder(this.mContext, PRIMARY_CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(content)
            .setSmallIcon(R.drawable.ic_notify)
            .setAutoCancel(true)
            .setContentIntent(notificationPendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(NotificationCompat.DEFAULT_ALL)

    }

    fun cancelNotification(id: Int) {
        mNotifyManager.cancel(id)
    }

    fun cancelAllNotifications() {
        mNotifyManager.cancelAll()
    }

}