package com.doc.droidysdroidsclock.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.core.app.NotificationCompat
import com.doc.droidysdroidsclock.R
import com.doc.droidysdroidsclock.TimerActivity
import com.doc.droidysdroidsclock.TimerNotifReceiver
import java.text.SimpleDateFormat
import java.util.*

class TimerNotifUtil {
    companion object {
        private const val CHANNEL_ID = "menuTimer"
        private const val CHANNEL_NAME = "Droidsy Droids Timer"
        private const val TIMER_ID = 0

        fun showTimerFinished(context: Context) {
            val startIntent = Intent(context, TimerNotifReceiver::class.java)
            startIntent.action = "start"
            val startPendingIntent = PendingIntent.getBroadcast(context, 0, startIntent, PendingIntent.FLAG_UPDATE_CURRENT)

            val notifBuilder = getNotifBuilder(context, CHANNEL_ID, true)
            notifBuilder.setContentTitle("Timer Complete").setContentText("Your timer has finished.").setContentIntent(
                getPendingIntentWithStack(context, TimerActivity::class.java)).addAction(R.drawable.ic_resume_btn, "Start", startPendingIntent)

            val notifManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notifManager.createNotificationChannel(CHANNEL_ID, CHANNEL_NAME, true)

            notifManager.notify(TIMER_ID, notifBuilder.build())
        }

        fun showTimerOngoing(context: Context, alertTime: Long) {
            val stopIntent = Intent(context, TimerNotifReceiver::class.java)
            stopIntent.action = "stop"
            val stopPendingIntent = PendingIntent.getBroadcast(context, 0, stopIntent, PendingIntent.FLAG_UPDATE_CURRENT)

            val pauseIntent = Intent(context, TimerNotifReceiver::class.java)
            pauseIntent.action = "pause"
            val pausePendingIntent = PendingIntent.getBroadcast(context, 0, pauseIntent, PendingIntent.FLAG_UPDATE_CURRENT)

            val dateFormat = SimpleDateFormat.getTimeInstance(SimpleDateFormat.SHORT)

            val notifBuilder = getNotifBuilder(context, CHANNEL_ID, true)
            notifBuilder.setContentTitle("Timer Running").setContentText("End: ${dateFormat.format(Date(alertTime))}").setOngoing(true).setContentIntent(
                getPendingIntentWithStack(context, TimerActivity::class.java)).addAction(R.drawable.ic_stop_btn, "Stop", stopPendingIntent).addAction(R.drawable.ic_pause_button, "Pause", pausePendingIntent)

            val notifManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notifManager.createNotificationChannel(CHANNEL_ID, CHANNEL_NAME, true)

            notifManager.notify(TIMER_ID, notifBuilder.build())
        }

        fun showTimerPaused(context: Context) {
            val resumeIntent = Intent(context, TimerNotifReceiver::class.java)
            resumeIntent.action = "resume"
            val resumePendingIntent = PendingIntent.getBroadcast(context, 0, resumeIntent, PendingIntent.FLAG_UPDATE_CURRENT)

            val notifBuilder = getNotifBuilder(context, CHANNEL_ID, true)
            notifBuilder.setContentTitle("Timer is paused").setContentText("Resume?").setOngoing(true).setContentIntent(
                getPendingIntentWithStack(context, TimerActivity::class.java)).addAction(R.drawable.ic_resume_btn, "Start", resumePendingIntent)

            val notifManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notifManager.createNotificationChannel(CHANNEL_ID, CHANNEL_NAME, true)

            notifManager.notify(TIMER_ID, notifBuilder.build())
        }

        fun hideNotif(context: Context) {
            val notifManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notifManager.cancel(TIMER_ID)
        }

        private fun getNotifBuilder(context: Context, channelId: String, playSound: Boolean): NotificationCompat.Builder {
            val sound: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val notifBuilder = NotificationCompat.Builder(context, channelId).setSmallIcon(R.drawable.ic_start_timer).setAutoCancel(true).setDefaults(0)

            if(playSound) {
                notifBuilder.setSound(sound)
            }
            return notifBuilder
        }

        private fun <T> getPendingIntentWithStack(context: Context, javaClass: Class<T>): PendingIntent {
            val resultIntent = Intent(context, javaClass)
            resultIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP

            val stackBuilder = TaskStackBuilder.create(context)
            stackBuilder.addParentStack(javaClass)
            stackBuilder.addNextIntent(resultIntent)

            return stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        private fun NotificationManager.createNotificationChannel(channelID: String, channelName: String, playSound: Boolean) {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channelImportance = if(playSound) NotificationManager.IMPORTANCE_DEFAULT
                else NotificationManager.IMPORTANCE_LOW
                val notifChannel = NotificationChannel(channelID, channelName, channelImportance)
                notifChannel.enableLights(true)
                notifChannel.lightColor = Color.BLUE
                this.createNotificationChannel(notifChannel)
            }
        }
    }
}