package com.doc.droidysdroidsclock

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.doc.droidysdroidsclock.util.PrefUtil
import com.doc.droidysdroidsclock.util.TimerNotifUtil

class TimerFinishedReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        TimerNotifUtil.showTimerFinished(context)

        PrefUtil.setState(TimerActivity.TimerState.Stopped, context)
        PrefUtil.setAlarmTime(0, context)
    }
}