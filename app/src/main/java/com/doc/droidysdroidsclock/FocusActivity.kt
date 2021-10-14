package com.doc.droidysdroidsclock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView

class FocusActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_focus)

        // BUTTONS
        val clockTab: Button = findViewById(R.id.button)
        clockTab.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
            }
        }
        val alarmTab: Button = findViewById(R.id.button2)
        alarmTab.setOnClickListener {
            Intent(this, AlarmActivity::class.java).also {
                startActivity(it)
            }
        }
        val stopwatchTab: Button = findViewById(R.id.button3)
        stopwatchTab.setOnClickListener {
            Intent(this, StopwatchActivity::class.java).also {
                startActivity(it)
            }
        }
        val timerTab: Button = findViewById(R.id.button4)
        timerTab.setOnClickListener {
            Intent(this, TimerActivity::class.java).also {
                startActivity(it)
            }
        }

        val editWrk: EditText = findViewById(R.id.editWork)
        val editBrk1: EditText = findViewById(R.id.editBrk1)
        val editBrk2: EditText = findViewById(R.id.editBrk2)
        val sessionLabel: TextView = findViewById(R.id.session_label)
        val timeRemaining: TextView = findViewById(R.id.time_remaining)
        val pauseBtn: ImageButton = findViewById(R.id.pause_focus)
        val soundOnBtn: ImageButton = findViewById(R.id.sound_on_focus)
        val muteBtn: ImageButton = findViewById(R.id.sound_focus)
        val cancelFocus: ImageButton = findViewById(R.id.cancel_focus)
        val wrkTime: TextView = findViewById(R.id.time_for_work)
        val brk1Time: TextView = findViewById(R.id.time_for_break1)
        val brk2Time: TextView = findViewById(R.id.time_for_break2)
        val beginFocus: ImageButton = findViewById(R.id.begin_focus)


        sessionLabel.visibility = View.GONE
        timeRemaining.visibility = View.GONE
        pauseBtn.visibility = View.GONE
        soundOnBtn.visibility = View.GONE
        cancelFocus.visibility = View.GONE


        beginFocus.setOnClickListener {
            sessionLabel.visibility = View.VISIBLE
            timeRemaining.visibility = View.VISIBLE
            pauseBtn.visibility = View.VISIBLE
            soundOnBtn.visibility = View.VISIBLE

            editWrk.visibility = View.GONE
            editBrk1.visibility = View.GONE
            editBrk2.visibility = View.GONE
            beginFocus.visibility = View.GONE
            muteBtn.visibility = View.GONE
            wrkTime.visibility = View.GONE
            brk1Time.visibility = View.GONE
            brk2Time.visibility = View.GONE
        }

        pauseBtn.setOnClickListener {
            pauseBtn.visibility = View.GONE
            soundOnBtn.visibility = View.GONE
            cancelFocus.visibility = View.VISIBLE
        }

        cancelFocus.setOnClickListener {
            sessionLabel.visibility = View.GONE
            timeRemaining.visibility = View.GONE
            cancelFocus.visibility = View.GONE

            editWrk.visibility = View.VISIBLE
            editBrk1.visibility = View.VISIBLE
            editBrk2.visibility = View.VISIBLE
            beginFocus.visibility = View.VISIBLE
            muteBtn.visibility = View.VISIBLE
            wrkTime.visibility = View.VISIBLE
            brk1Time.visibility = View.VISIBLE
            brk2Time.visibility = View.VISIBLE
        }

    }
}