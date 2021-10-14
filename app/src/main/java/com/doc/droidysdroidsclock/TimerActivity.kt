package com.doc.droidysdroidsclock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.NumberPicker
import android.widget.TextView

class TimerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        // BUTTONS
        val clockTab: Button = findViewById(R.id.button)
        clockTab.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                overridePendingTransition(0, 0);
            }
        }
        val alarmTab: Button = findViewById(R.id.button2)
        alarmTab.setOnClickListener {
            Intent(this, AlarmActivity::class.java).also {
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
        val focusTab: Button = findViewById(R.id.button5)
        focusTab.setOnClickListener {
            Intent(this, FocusActivity::class.java).also {
                startActivity(it)
                overridePendingTransition(0, 0);
            }
        }


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