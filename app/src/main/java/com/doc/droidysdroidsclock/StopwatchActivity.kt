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
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.doc.droidysdroidsclock.util.Mutables

class StopwatchActivity : AppCompatActivity() {

    private var timeElapsed = 0.0
    private var stopped: Boolean = true
    private var paused: Boolean = false
    private lateinit var serviceIntent: Intent

class StopwatchActivity : AppCompatActivity() {

    private var x1:Float = 0.0F
    private var x2:Float = 0.0F
    private var y1:Float = 0.0F
    private var y2:Float = 0.0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stopwatch)

        val cl = findViewById(R.id.stopwatch_page) as ConstraintLayout
        if (Mutables.stopwatch === "gradient1") {
            cl.setBackgroundResource(R.drawable.gradient1)
        } else if (Mutables.stopwatch === "gradient2") {
            cl.setBackgroundResource(R.drawable.gradient2)
        }else {
            cl.setBackgroundResource(R.drawable.gradient3)
        }

        // BUTTONS
        val clockTab: Button = findViewById(R.id.clock_button)
        clockTab.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                overridePendingTransition(0, 0)
            }
        }
        val alarmTab: Button = findViewById(R.id.alarm_button)
        alarmTab.setOnClickListener {
            Intent(this, AlarmActivity::class.java).also {
                startActivity(it)
                overridePendingTransition(0, 0)
            }
        }
        val timerTab: Button = findViewById(R.id.timer_button)
        timerTab.setOnClickListener {
            Intent(this, TimerActivity::class.java).also {
                startActivity(it)
                overridePendingTransition(0, 0)
            }
        }
        val focusTab: Button = findViewById(R.id.focus_button)
        focusTab.setOnClickListener {
            Intent(this, FocusActivity::class.java).also {
                startActivity(it)
                overridePendingTransition(0, 0)
            }
        }

        serviceIntent = Intent(applicationContext, StopwatchService::class.java)
        registerReceiver(updateTime, IntentFilter(StopwatchService.TIMER_UPDATED))
        
        val customiseBtn: ImageButton = findViewById(R.id.customise_button)
        customiseBtn.setOnClickListener {
            Mutables.previousPage = "StopwatchActivity"
            Intent(this, CustomiseActivity::class.java).also {
                startActivity(it)
                overridePendingTransition(0, 0)
            }
        }

        fun startAlarm(){
            Intent(this, AlarmActivity::class.java).also {
                startActivity(it)
                overridePendingTransition(0, 0)
            }
        }
        fun startTimer(){
            Intent(this, TimerActivity::class.java).also {
                startActivity(it)
                overridePendingTransition(0, 0)
            }
        }

        val activity_stopwatch = findViewById<View>(android.R.id.content).getRootView()
        activity_stopwatch.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View, m: MotionEvent): Boolean {
                if (m.action === MotionEvent.ACTION_DOWN) {
                    x1 = m.x
                    y1 = m.y
                } else if (m.action === MotionEvent.ACTION_UP) {
                    x2 = m.x
                    y2 = m.y
                    if (x1 < x2) {
                        //swiped right
                        startAlarm()
                    }else if (x1 > x2) {
                        //swiped left
                        startTimer()
                    }
                }
                return false
            }
        })

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