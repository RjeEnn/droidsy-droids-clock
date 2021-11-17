package com.doc.droidysdroidsclock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.NumberPicker
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.doc.droidysdroidsclock.util.Mutables

class TimerActivity : AppCompatActivity() {

    private var x1:Float = 0.0F
    private var x2:Float = 0.0F
    private var y1:Float = 0.0F
    private var y2:Float = 0.0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        val cl = findViewById(R.id.timer_page) as ConstraintLayout
        if (Mutables.timer === "gradient1") {
            cl.setBackgroundResource(R.drawable.gradient1)
        } else if (Mutables.timer === "gradient2") {
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
        val stopwatchTab: Button = findViewById(R.id.stopwatch_button)
        stopwatchTab.setOnClickListener {
            Intent(this, StopwatchActivity::class.java).also {
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
            Mutables.previousPage = "TimerActivity"
            Intent(this, CustomiseActivity::class.java).also {
                startActivity(it)
                overridePendingTransition(0, 0)
            }
        }

        val settingsBtn: ImageButton = findViewById(R.id.settings_button)
        customiseBtn.setOnClickListener {
            Mutables.previousPage = "TimerActivity"
            Intent(this, SettingsActivity::class.java).also {
                startActivity(it)
                overridePendingTransition(0, 0)
            }
        }

        val worldClockTab: Button = findViewById(R.id.world_clock_button)
        if (!Mutables.showAlarm) { alarmTab.visibility = View.GONE }
        if (!Mutables.showStopwatch) { stopwatchTab.visibility = View.GONE }
        if (!Mutables.showFocus) { focusTab.visibility = View.GONE }
        if (!Mutables.showWorldClock) { worldClockTab.visibility = View.GONE }

        fun startStopwatch(){
            Intent(this, StopwatchActivity::class.java).also {
                startActivity(it)
                overridePendingTransition(0, 0)
            }
        }
        fun startFocus(){
            Intent(this, FocusActivity::class.java).also {
                startActivity(it)
                overridePendingTransition(0, 0)
            }
        }

        val activity_timer = findViewById<View>(android.R.id.content).getRootView()
        activity_timer.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View, m: MotionEvent): Boolean {
                if (m.action === MotionEvent.ACTION_DOWN) {
                    x1 = m.x
                    y1 = m.y
                } else if (m.action === MotionEvent.ACTION_UP) {
                    x2 = m.x
                    y2 = m.y
                    if (x1 < x2) {
                        //swiped right
                        startStopwatch()
                    }else if (x1 > x2) {
                        //swiped left
                        startFocus()
                    }
                }
                return false
            }
        })

        val hrPicker: NumberPicker = findViewById(R.id.timer_hr)
        val minPicker: NumberPicker = findViewById(R.id.timer_min)
        val secPicker: NumberPicker = findViewById(R.id.timer_sec)

        val hoursM = (0..23).toList().toTypedArray()
        val hours = hoursM.map { it.toString() }.toTypedArray()
        hrPicker.minValue = 0
        hrPicker.maxValue = hours.size - 1
        hrPicker.displayedValues = hours

        val minsM = (0..59).toList().toTypedArray()
        val min = minsM.map { it.toString() }.toTypedArray()
        minPicker.minValue = 0
        minPicker.maxValue = min.size - 1
        minPicker.displayedValues = min

        val secsM = (0..59).toList().toTypedArray()
        val secs = secsM.map { it.toString() }.toTypedArray()
        secPicker.minValue = 0
        secPicker.maxValue = min.size - 1
        secPicker.displayedValues = secs


        val union: View = findViewById(R.id.union2)
        val ellipse: View = findViewById(R.id.ellipse)
        val cancelBtn: ImageButton = findViewById(R.id.cancel_timer)
        val timerBtn: ImageButton = findViewById(R.id.start_timer)
        val formatTxt: TextView = findViewById(R.id.format)
        val add15: ImageButton = findViewById(R.id.add15)
        val sub15: ImageButton = findViewById(R.id.sub15)

        add15.visibility = View.GONE
        sub15.visibility = View.GONE
        cancelBtn.visibility = View.GONE
        union.visibility = View.GONE

        timerBtn.setOnClickListener {
            ellipse.visibility = View.GONE
            formatTxt.visibility = View.GONE
            timerBtn.visibility = View.GONE

            add15.visibility = View.VISIBLE
            sub15.visibility = View.VISIBLE
            cancelBtn.visibility = View.VISIBLE
            union.visibility = View.VISIBLE
        }

        cancelBtn.setOnClickListener {
            ellipse.visibility = View.VISIBLE
            formatTxt.visibility = View.VISIBLE
            timerBtn.visibility = View.VISIBLE

            add15.visibility = View.GONE
            sub15.visibility = View.GONE
            cancelBtn.visibility = View.GONE
            union.visibility = View.GONE
        }
    }
}