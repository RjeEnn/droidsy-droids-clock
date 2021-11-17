package com.doc.droidysdroidsclock

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextClock
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import com.doc.droidysdroidsclock.util.Mutables
import java.text.DateFormat
import java.util.*

class WorldClockActivity : AppCompatActivity() {

    private var x1:Float = 0.0F
    private var x2:Float = 0.0F
    private var y1:Float = 0.0F
    private var y2:Float = 0.0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_world_clock)

        val cl = findViewById(R.id.world_clock_page) as ConstraintLayout
        if (Mutables.world_clock === "gradient1") {
            cl.setBackgroundResource(R.drawable.gradient1)
        } else if (Mutables.world_clock === "gradient2") {
            cl.setBackgroundResource(R.drawable.gradient2)
        } else if (Mutables.world_clock === "gradient3") {
            cl.setBackgroundResource(R.drawable.gradient3)
        } else if (Mutables.world_clock === "gradient4") {
            cl.setBackgroundResource(R.drawable.gradient4)
        } else if (Mutables.world_clock === "gradient5") {
            cl.setBackgroundResource(R.drawable.gradient5)
        } else if (Mutables.world_clock === "gradient6") {
            cl.setBackgroundResource(R.drawable.gradient6)
        } else if (Mutables.world_clock === "gradient7") {
            cl.setBackgroundResource(R.drawable.gradient7)
        }else {
            cl.setBackgroundResource(R.drawable.gradient8)
        }

        // BUTTONS
        val clockTab: Button = findViewById(R.id.clock_button)
        clockTab.setOnClickListener {
            Intent(this, AlarmActivity::class.java).also {
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
        val stopwatchTab: Button = findViewById(R.id.stopwatch_button)
        stopwatchTab.setOnClickListener {
            Intent(this, StopwatchActivity::class.java).also {
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
            Mutables.previousPage = "WorldClockActivity"
            Intent(this, CustomiseActivity::class.java).also {
                startActivity(it)
                overridePendingTransition(0, 0)
            }
        }

        val settingsBtn: ImageButton = findViewById(R.id.settings_button)
        settingsBtn.setOnClickListener {
            Mutables.previousPage = "WorldClockActivity"
            Intent(this, SettingsActivity::class.java).also {
                startActivity(it)
                overridePendingTransition(0, 0)
            }
        }

        if (!Mutables.showAlarm) { alarmTab.visibility = View.GONE }
        if (!Mutables.showStopwatch) { stopwatchTab.visibility = View.GONE }
        if (!Mutables.showTimer) { timerTab.visibility = View.GONE }
        if (!Mutables.showFocus) { focusTab.visibility = View.GONE }

        fun startAlarmActivity(){
            Intent(this, AlarmActivity::class.java).also {
                startActivity(it)
                overridePendingTransition(0, 0)
            }
        }

        val activity_main = findViewById<View>(android.R.id.content).getRootView()
        activity_main.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View, m: MotionEvent): Boolean {
                if (m.action === MotionEvent.ACTION_DOWN) {
                    x1 = m.x
                    y1 = m.y
                } else if (m.action === MotionEvent.ACTION_UP) {
                    x2 = m.x
                    y2 = m.y
                    if (x1 > x2) {
                        //swiped left
                        startAlarmActivity()
                    }
                }
                return false
            }
        })

        val c: Context = applicationContext
        val timeClock: TextClock = findViewById(R.id.ourTimeClock)
        val typeface = ResourcesCompat.getFont(c,R.font.comfortaa_bold)
        timeClock.typeface = typeface

        val c2: Context = applicationContext
        val timeClock2 : TextClock = findViewById(R.id.alaskaClock)
        timeClock2.timeZone = "GMT-9"
        val typeface2 = ResourcesCompat.getFont(c2,R.font.comfortaa_bold)
        timeClock2.typeface = typeface2

        val c3: Context = applicationContext
        val timeClock3: TextClock = findViewById(R.id.japanClock)
        timeClock3.timeZone = "GMT+9"
        val typeface3 = ResourcesCompat.getFont(c3,R.font.comfortaa_bold)
        timeClock3.typeface = typeface3


    }
}