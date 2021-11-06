package com.doc.droidysdroidsclock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

class StopwatchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stopwatch)

        // BUTTONS
        val clockTab: Button = findViewById(R.id.clock_button)
        clockTab.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                overridePendingTransition(0, 0);
            }
        }
        val alarmTab: Button = findViewById(R.id.alarm_button)
        alarmTab.setOnClickListener {
            Intent(this, AlarmActivity::class.java).also {
                startActivity(it)
                overridePendingTransition(0, 0);
            }
        }
        val timerTab: Button = findViewById(R.id.timer_button)
        timerTab.setOnClickListener {
            Intent(this, TimerActivity::class.java).also {
                startActivity(it)
                overridePendingTransition(0, 0);
            }
        }
        val focusTab: Button = findViewById(R.id.focus_button)
        focusTab.setOnClickListener {
            Intent(this, FocusActivity::class.java).also {
                startActivity(it)
                overridePendingTransition(0, 0);
            }
        }


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