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

class MainActivity : AppCompatActivity() {

    private var x1:Float = 0.0F
    private var x2:Float = 0.0F
    private var y1:Float = 0.0F
    private var y2:Float = 0.0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cl = findViewById(R.id.main_page) as ConstraintLayout
        if (Mutables.main === "gradient1") {
            cl.setBackgroundResource(R.drawable.gradient1)
        } else if (Mutables.main === "gradient2") {
            cl.setBackgroundResource(R.drawable.gradient2)
        }else {
            cl.setBackgroundResource(R.drawable.gradient3)
        }

        // BUTTONS
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
            Mutables.previousPage = "MainActivity"
            Intent(this, CustomiseActivity::class.java).also {
                startActivity(it)
                overridePendingTransition(0, 0)
            }
        }

        val settingsBtn: ImageButton = findViewById(R.id.settings_button)
        settingsBtn.setOnClickListener {
            Mutables.previousPage = "MainActivity"
            Intent(this, SettingsActivity::class.java).also {
                startActivity(it)
                overridePendingTransition(0, 0)
            }
        }

        val worldClockTab: Button = findViewById(R.id.world_clock_button)
        if (!Mutables.showAlarm) { alarmTab.visibility = View.GONE }
        if (!Mutables.showStopwatch) { stopwatchTab.visibility = View.GONE }
        if (!Mutables.showTimer) { timerTab.visibility = View.GONE }
        if (!Mutables.showFocus) { focusTab.visibility = View.GONE }
        if (!Mutables.showWorldClock) { worldClockTab.visibility = View.GONE }

        fun startAlarm(){
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
                        startAlarm()
                    }
                }
                return false
            }
        })

        val calendar: Calendar = Calendar.getInstance()
        val currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.time)
        val textViewDate: TextView = findViewById(R.id.dateText)
        textViewDate.text = currentDate.toString().lowercase()

        val c: Context = applicationContext
        val timeClock: TextClock = findViewById(R.id.digitalClock)
        val typeface = ResourcesCompat.getFont(c,R.font.comfortaa_bold)
        timeClock.typeface = typeface
    }
}