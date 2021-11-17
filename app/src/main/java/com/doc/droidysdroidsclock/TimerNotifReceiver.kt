package com.doc.droidysdroidsclock

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.doc.droidysdroidsclock.util.PrefUtil
import com.doc.droidysdroidsclock.util.TimerNotifUtil

class TimerNotifReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        when(intent.action) {
            "stop" -> {
                TimerActivity.removeAlarm(context)
                PrefUtil.setState(TimerActivity.TimerState.Stopped, context)

                TimerNotifUtil.hideNotif(context)
            }

            "pause" -> {
                var timeRemaining = PrefUtil.getTimeRemaining(context)
                var alarmTime = PrefUtil.getAlarmTime(context)
                val elapsed = TimerActivity.elapsed

                timeRemaining -= elapsed -alarmTime
                PrefUtil.setTimeRemaining(timeRemaining, context)

                TimerActivity.removeAlarm(context)
                PrefUtil.setState(TimerActivity.TimerState.Paused, context)
                TimerNotifUtil.showTimerPaused(context)
            }

            "resume" -> {
                val timeRemaining = PrefUtil.getTimeRemaining(context)
                val alertTime = TimerActivity.setAlarm(context, TimerActivity.elapsed, timeRemaining)

                PrefUtil.setState(TimerActivity.TimerState.Running, context)
                TimerNotifUtil.showTimerOngoing(context, alertTime)
            }

            "start" -> {
                val timerLength = PrefUtil.getTimerLength(context)
                val timeRemaining = timerLength.toLong()
                val alertTime = TimerActivity.setAlarm(context, TimerActivity.elapsed, timeRemaining)

                PrefUtil.setState(TimerActivity.TimerState.Running, context)
                PrefUtil.setTimeRemaining(timeRemaining, context)
                TimerNotifUtil.showTimerOngoing(context, alertTime)
            }
        }
    }
}