package com.doc.droidysdroidsclock

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.NumberPicker
import android.widget.TextClock
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.doc.droidysdroidsclock.util.Mutables

class SetAlarmActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_alarm)

        val cl = findViewById(R.id.set_alarm_page) as ConstraintLayout
        if (Mutables.set_alarm === "gradient1") {
            cl.setBackgroundResource(R.drawable.gradient1)
        } else if (Mutables.set_alarm === "gradient2") {
            cl.setBackgroundResource(R.drawable.gradient2)
        } else if (Mutables.set_alarm === "gradient3") {
            cl.setBackgroundResource(R.drawable.gradient3)
        } else if (Mutables.set_alarm === "gradient4") {
            cl.setBackgroundResource(R.drawable.gradient4)
        } else if (Mutables.set_alarm === "gradient5") {
            cl.setBackgroundResource(R.drawable.gradient5)
        } else if (Mutables.set_alarm === "gradient6") {
            cl.setBackgroundResource(R.drawable.gradient6)
        } else if (Mutables.set_alarm === "gradient7") {
            cl.setBackgroundResource(R.drawable.gradient7)
        }else {
            cl.setBackgroundResource(R.drawable.gradient8)
        }

        val clockTab: Button = findViewById(R.id.clock_button)
        clockTab.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
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

            Mutables.previousPage = "SetAlarmActivity"
            Intent(this, CustomiseActivity::class.java).also {
                startActivity(it)
                overridePendingTransition(0, 0)
            }
        }

        val settingsBtn: ImageButton = findViewById(R.id.settings_button)
        settingsBtn.setOnClickListener {
            Mutables.previousPage = "SetAlarmActivity"
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

        if (!Mutables.showStopwatch) { stopwatchTab.visibility = View.GONE }
        if (!Mutables.showTimer) { timerTab.visibility = View.GONE }
        if (!Mutables.showFocus) { focusTab.visibility = View.GONE }
        if (!Mutables.showWorldClock) { worldClockTab.visibility = View.GONE }


        val hrPicker: NumberPicker = findViewById(R.id.alrm_hr)
        val minPicker: NumberPicker = findViewById(R.id.alrm_min)

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

        val a1: TextView = findViewById(R.id.alm_time1)
        val a2: TextView = findViewById(R.id.alm_time2)
        val a3: TextView = findViewById(R.id.alm_time3)
        val a4: TextView = findViewById(R.id.alm_time4)
        val a5: TextView = findViewById(R.id.alm_time5)

        val setAlmBtn: Button = findViewById(R.id.set_alm)
        setAlmBtn.setOnClickListener {
            {
                a1.setText("11")
            }

        }
    }
}