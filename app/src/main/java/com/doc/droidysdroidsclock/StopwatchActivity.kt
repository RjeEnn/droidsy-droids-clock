package com.doc.droidysdroidsclock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.doc.droidysdroidsclock.util.Mutables

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
        } else if (Mutables.stopwatch === "gradient3") {
            cl.setBackgroundResource(R.drawable.gradient3)
        } else if (Mutables.stopwatch === "gradient4") {
            cl.setBackgroundResource(R.drawable.gradient4)
        } else if (Mutables.stopwatch === "gradient5") {
            cl.setBackgroundResource(R.drawable.gradient5)
        } else if (Mutables.stopwatch === "gradient6") {
            cl.setBackgroundResource(R.drawable.gradient6)
        } else if (Mutables.stopwatch === "gradient7") {
            cl.setBackgroundResource(R.drawable.gradient7)
        }else {
            cl.setBackgroundResource(R.drawable.gradient8)
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
        val customiseBtn: ImageButton = findViewById(R.id.customise_button)
        customiseBtn.setOnClickListener {
            Mutables.previousPage = "StopwatchActivity"
            Intent(this, CustomiseActivity::class.java).also {
                startActivity(it)
                overridePendingTransition(0, 0)
            }
        }

        val settingsBtn: ImageButton = findViewById(R.id.settings_button)
        settingsBtn.setOnClickListener {
            Mutables.previousPage = "StopwatchActivity"
            Intent(this, SettingsActivity::class.java).also {
                startActivity(it)
                overridePendingTransition(0, 0)
            }
        }

        val worldClockTab: Button = findViewById(R.id.world_clock_button)
        worldClockTab.setOnClickListener {
            Intent(this, WorldClockActivity::class.java).also {
                startActivity(it)
                overridePendingTransition(0, 0)
            }
        }

        if (!Mutables.showAlarm) { alarmTab.visibility = View.GONE }
        if (!Mutables.showTimer) { timerTab.visibility = View.GONE }
        if (!Mutables.showFocus) { focusTab.visibility = View.GONE }
        if (!Mutables.showWorldClock) { worldClockTab.visibility = View.GONE }

        fun startAlarmActivity(){
            Intent(this, AlarmActivity::class.java).also {
                startActivity(it)
                overridePendingTransition(0, 0)
            }
        }
        fun startTimerActivity(){
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
                        startAlarmActivity()
                    }else if (x1 > x2) {
                        //swiped left
                        startTimerActivity()
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
        val ellipse: View = findViewById(R.id.ellipse_1)

        timing.visibility = View.GONE
        format.visibility = View.GONE
        stopBtn.visibility = View.GONE
        lapBtn.visibility = View.GONE
        resetBtn.visibility = View.GONE
        resumeBtn.visibility = View.GONE
        union.visibility = View.GONE

        startBtn.setOnClickListener {
            startBtn.visibility = View.GONE
            ellipse.visibility = View.GONE

            timing.visibility = View.VISIBLE
            format.visibility = View.VISIBLE
            stopBtn.visibility = View.VISIBLE
            lapBtn.visibility = View.VISIBLE
            union.visibility = View.VISIBLE
        }

        stopBtn.setOnClickListener {
            stopBtn.visibility = View.GONE
            lapBtn.visibility = View.GONE

            resetBtn.visibility = View.VISIBLE
            resumeBtn.visibility = View.VISIBLE
        }

        resumeBtn.setOnClickListener {
            stopBtn.visibility = View.VISIBLE
            lapBtn.visibility = View.VISIBLE

            resetBtn.visibility = View.GONE
            resumeBtn.visibility = View.GONE
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
            ellipse.visibility = View.VISIBLE
        }
    }
}