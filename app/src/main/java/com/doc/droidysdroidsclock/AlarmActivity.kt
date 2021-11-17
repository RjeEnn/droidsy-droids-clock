package com.doc.droidysdroidsclock

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.doc.droidysdroidsclock.util.Mutables

class AlarmActivity: AppCompatActivity() {

    private var x1:Float = 0.0F
    private var x2:Float = 0.0F
    private var y1:Float = 0.0F
    private var y2:Float = 0.0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)

        val cl = findViewById(R.id.alarm_page) as ConstraintLayout
        if (Mutables.alarm === "gradient1") {
            cl.setBackgroundResource(R.drawable.gradient1)
        } else if (Mutables.alarm === "gradient2") {
            cl.setBackgroundResource(R.drawable.gradient2)
        }else {
            cl.setBackgroundResource(R.drawable.gradient3)
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


        val setBtn: Button = findViewById(R.id.new_alm_btn)
        setBtn.setOnClickListener {
            Intent(this, SetAlarmActivity::class.java).also {
                startActivity(it)
            }
        }

        val customiseBtn: ImageButton = findViewById(R.id.customise_button)
        customiseBtn.setOnClickListener {
            Mutables.previousPage = "AlarmActivity"
            Intent(this, CustomiseActivity::class.java).also {
                startActivity(it)
                overridePendingTransition(0, 0)
            }
        }

        fun startMain(){
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                overridePendingTransition(0, 0)
            }
        }
        fun startStopwatch(){
            Intent(this, StopwatchActivity::class.java).also {
                startActivity(it)
                overridePendingTransition(0, 0)
            }
        }

        val activity_alarm = findViewById<View>(android.R.id.content).getRootView()
        activity_alarm.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View, m: MotionEvent): Boolean {
                if (m.action === MotionEvent.ACTION_DOWN) {
                    x1 = m.x
                    y1 = m.y
                } else if (m.action === MotionEvent.ACTION_UP) {
                    x2 = m.x
                    y2 = m.y
                    if (x1 < x2) {
                        //swiped right
                        startMain()
                    }else if (x1 > x2) {
                        //swiped left
                        startStopwatch()
                    }
                }
                return false
            }
        })
    }
}