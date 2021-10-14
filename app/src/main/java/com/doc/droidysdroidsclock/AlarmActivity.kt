package com.doc.droidysdroidsclock

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class AlarmActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)

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


        val setBtn: Button = findViewById(R.id.new_alm_btn)
        setBtn.setOnClickListener {
            Intent(this, SetAlarmActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}