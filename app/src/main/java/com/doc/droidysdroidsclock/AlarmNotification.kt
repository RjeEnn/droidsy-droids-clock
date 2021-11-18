package com.doc.droidysdroidsclock

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer

class AlarmNotification: BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            val mediaFile = MediaPlayer.create(context, R.raw.ringtone1)
            mediaFile.start()
        }
}