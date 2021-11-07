package com.doc.droidysdroidsclock

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import java.util.*

// Service that will allow the stopwatch to run in the background
class StopwatchService: Service() {
    override fun onBind(p0: Intent?): IBinder? = null

    // Initializing the stopwatch
    private val stopwatch = Timer()

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        // Receives time from the stopwatch activity
        val time = intent.getDoubleExtra(TIME_EXTRA, 0.0)
        stopwatch.scheduleAtFixedRate(TimeTask(time), 0, 1000)
        return START_STICKY
    }

    override fun onDestroy() {
        stopwatch.cancel()
        super.onDestroy()
    }

    private inner class TimeTask(private var time: Double) : TimerTask() {

        override fun run() {
            val intent = Intent(TIMER_UPDATED)
            time++
            intent.putExtra(TIME_EXTRA, time)
            sendBroadcast(intent)
        }
    }

    companion object {
        const val TIMER_UPDATED = "timerUpdated"
        const val TIME_EXTRA = "timeExtra"
    }

}