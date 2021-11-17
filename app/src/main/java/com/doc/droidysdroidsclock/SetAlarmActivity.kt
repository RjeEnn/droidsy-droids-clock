package com.doc.droidysdroidsclock

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextClock
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat

class SetAlarmActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_alarm)

        val clockTab: Button = findViewById(R.id.button)
        clockTab.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                overridePendingTransition(0, 0);
            }
        }
        val stopwatchTab: Button = findViewById(R.id.button3)
        stopwatchTab.setOnClickListener {
            Intent(this, StopwatchActivity::class.java).also {
                startActivity(it)
                overridePendingTransition(0, 0);
            }
        }
        val timerTab: Button = findViewById(R.id.button4)
        timerTab.setOnClickListener {
            Intent(this, TimerActivity::class.java).also {
                startActivity(it)
                overridePendingTransition(0, 0);
            }
        }
        val focusTab: Button = findViewById(R.id.button5)
        focusTab.setOnClickListener {
            Intent(this, FocusActivity::class.java).also {
                startActivity(it)
                overridePendingTransition(0, 0);
            }
        }

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