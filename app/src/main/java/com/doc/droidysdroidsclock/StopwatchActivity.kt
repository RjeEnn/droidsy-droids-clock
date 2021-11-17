package com.doc.droidysdroidsclock

import android.animation.ObjectAnimator
import android.content.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.widget.*
import kotlin.math.roundToInt

class StopwatchActivity : AppCompatActivity() {

    private var timeElapsed = 0.0
    private var stopped: Boolean = true
    private var paused: Boolean = false
    private lateinit var serviceIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stopwatch)

        // BUTTONS
        val clockTab: Button = findViewById(R.id.button)
        clockTab.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                overridePendingTransition(0, 0)
            }
        }
        val alarmTab: Button = findViewById(R.id.button2)
        alarmTab.setOnClickListener {
            Intent(this, AlarmActivity::class.java).also {
                startActivity(it)
                overridePendingTransition(0, 0)
            }
        }
        val timerTab: Button = findViewById(R.id.button4)
        timerTab.setOnClickListener {
            Intent(this, TimerActivity::class.java).also {
                startActivity(it)
                overridePendingTransition(0, 0)
            }
        }
        val focusTab: Button = findViewById(R.id.button5)
        focusTab.setOnClickListener {
            Intent(this, FocusActivity::class.java).also {
                startActivity(it)
                overridePendingTransition(0, 0)
            }
        }

        serviceIntent = Intent(applicationContext, StopwatchService::class.java)
        registerReceiver(updateTime, IntentFilter(StopwatchService.TIMER_UPDATED))

        val timing: TextView = findViewById(R.id.timing)
        val format: TextView = findViewById(R.id.format)
        val stopBtn: ImageButton = findViewById(R.id.stop_btn)
        val lapBtn: ImageButton = findViewById(R.id.lap_btn)
        val resetBtn: ImageButton = findViewById(R.id.reset_btn)
        val resumeBtn: ImageButton = findViewById(R.id.resume_btn)
        val startBtn: Button = findViewById(R.id.start_stopwatch)
        val union: View = findViewById(R.id.union)

        val anim = ObjectAnimator.ofFloat(union, "rotation", 0f, 360f)

        anim.duration = 1000
        anim.repeatCount = Animation.INFINITE
        anim.repeatMode = ObjectAnimator.RESTART

        startBtn.setOnClickListener {
            startBtn.visibility = View.GONE

            timing.visibility = View.VISIBLE
            format.visibility = View.VISIBLE
            stopBtn.visibility = View.VISIBLE
            lapBtn.visibility = View.VISIBLE
            union.visibility = View.VISIBLE

            anim.start()

            serviceIntent.putExtra(StopwatchService.TIME_EXTRA, timeElapsed)
            startService(serviceIntent)

            stopped = false
            paused = false
        }

        stopBtn.setOnClickListener {
            stopBtn.visibility = View.GONE
            lapBtn.visibility = View.GONE

            resetBtn.visibility = View.VISIBLE
            resumeBtn.visibility = View.VISIBLE

            anim.pause()

            stopService(serviceIntent)

            stopped = false
            paused = true
        }

        resumeBtn.setOnClickListener {
            stopBtn.visibility = View.VISIBLE
            lapBtn.visibility = View.VISIBLE

            resetBtn.visibility = View.GONE
            resumeBtn.visibility = View.GONE

            anim.resume()

            serviceIntent.putExtra(StopwatchService.TIME_EXTRA, timeElapsed)
            startService(serviceIntent)

            stopped = false
            paused = false
        }

        resetBtn.setOnClickListener {
            union.visibility = View.GONE
            timing.visibility = View.GONE
            format.visibility = View.GONE
            stopBtn.visibility = View.GONE
            lapBtn.visibility = View.GONE
            resumeBtn.visibility = View.GONE
            resetBtn.visibility = View.GONE

            startBtn.visibility = View.VISIBLE

            stopService(serviceIntent)
            timeElapsed = 0.0
            timing.text = getTimeStringFromDouble(timeElapsed)

            stopped = true
            paused = false
        }

    }

    private val updateTime: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val timing: TextView = findViewById(R.id.timing)

            timeElapsed = intent.getDoubleExtra(StopwatchService.TIME_EXTRA, 0.0)
            timing.text = getTimeStringFromDouble(timeElapsed)
        }
    }

    private fun getTimeStringFromDouble(time: Double): String {
        val resultInt = time.roundToInt()
        val hrs = resultInt % 86400 / 3600
        val min = resultInt % 86400 % 3600 / 60
        val sec = resultInt % 86400 % 3600 % 60

        Log.i("test", timeElapsed.toString())

        return String.format("%02d:%02d:%02d", hrs, min, sec)
    }

    override fun onStop() {
        super.onStop()

        val prefs: SharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefs.edit()

        editor.putBoolean("stopped", stopped)
        editor.putBoolean("paused", paused)
        editor.putFloat("timeElapsed", timeElapsed.toFloat())

        editor.apply()
    }

    override fun onStart() {
        super.onStart()

        val timing: TextView = findViewById(R.id.timing)
        val format: TextView = findViewById(R.id.format)
        val stopBtn: ImageButton = findViewById(R.id.stop_btn)
        val lapBtn: ImageButton = findViewById(R.id.lap_btn)
        val resetBtn: ImageButton = findViewById(R.id.reset_btn)
        val resumeBtn: ImageButton = findViewById(R.id.resume_btn)
        val startBtn: Button = findViewById(R.id.start_stopwatch)
        val union: View = findViewById(R.id.union)

        val prefs: SharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE)
        timeElapsed = prefs.getFloat("timeElapsed", 0.0F).toDouble()
        stopped = prefs.getBoolean("stopped", true)
        paused = prefs.getBoolean("paused", false)

        if(stopped) {
            timing.visibility = View.GONE
            format.visibility = View.GONE
            stopBtn.visibility = View.GONE
            lapBtn.visibility = View.GONE
            resetBtn.visibility = View.GONE
            resumeBtn.visibility = View.GONE
            union.visibility = View.GONE

            startBtn.visibility = View.VISIBLE
        } else {
            if(paused) {
                stopBtn.visibility = View.GONE
                lapBtn.visibility = View.GONE
                startBtn.visibility = View.GONE

                resetBtn.visibility = View.VISIBLE
                resumeBtn.visibility = View.VISIBLE
                timing.visibility = View.VISIBLE
                union.visibility = View.VISIBLE
                format.visibility = View.VISIBLE
            } else {
                startBtn.visibility = View.GONE
                resetBtn.visibility = View.GONE
                resumeBtn.visibility = View.GONE

                timing.visibility = View.VISIBLE
                format.visibility = View.VISIBLE
                stopBtn.visibility = View.VISIBLE
                lapBtn.visibility = View.VISIBLE
                union.visibility = View.VISIBLE
            }
        }
    }
}