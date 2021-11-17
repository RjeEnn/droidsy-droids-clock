package com.doc.droidysdroidsclock

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.doc.droidysdroidsclock.util.Mutables

class CustomiseActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("FocusActivity", "created")
        setContentView(R.layout.activity_customise)

        var newGradient = 0
        var list_of_items = arrayOf("gradient1", "gradient2", "gradient3");

        val dropdown: Spinner = findViewById(R.id.theme_spinner);
        val aa = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, list_of_items);
        // Set layout to use when the list of choices appear
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Set Adapter to Spinner
        dropdown.adapter = aa
        dropdown.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?,selectedItemView: View,position: Int, id: Long
            ) {
                val rl = (R.layout.activity_customise) as ConstraintLayout
                if (position === 0) {
                    newGradient = R.drawable.gradient1
                } else if (position === 1) {
                    newGradient = R.drawable.gradient2
                }else {
                    newGradient = R.drawable.gradient3
                }
                rl.setBackgroundResource(newGradient)
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // your code here
            }
        }


        val clockCheck: CheckBox = findViewById(R.id.clockCheckBox);
        val alarmCheck: CheckBox = findViewById(R.id.alarmCheckBox);
        val stopwatchCheck: CheckBox = findViewById(R.id.stopwatchCheckBox);
        val timerCheck: CheckBox = findViewById(R.id.timerCheckBox);
        val focusCheck: CheckBox = findViewById(R.id.focusCheckBox);

        val allCheck: CheckBox = findViewById(R.id.allCheckBox)
        allCheck.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                clockCheck.isChecked = true
                alarmCheck.isChecked = true
                stopwatchCheck.isChecked = true
                timerCheck.isChecked = true
                focusCheck.isChecked = true

            }
        }

        val cancelBtn: Button = findViewById(R.id.cancel_button)
        cancelBtn.setOnClickListener {
            when {
                Mutables.previousPage === "MainActivity" -> {
                    Intent(this, MainActivity::class.java).also {
                        startActivity(it)
                        overridePendingTransition(0, 0);
                    }
                }
                Mutables.previousPage === "AlarmActivity" -> {
                    Intent(this, AlarmActivity::class.java).also {
                        startActivity(it)
                        overridePendingTransition(0, 0);
                    }
                }
                Mutables.previousPage === "StopwatchActivity" -> {
                    Intent(this, StopwatchActivity::class.java).also {
                        startActivity(it)
                        overridePendingTransition(0, 0);
                    }
                }
                Mutables.previousPage === "TimerActivity" -> {
                    Intent(this, TimerActivity::class.java).also {
                        startActivity(it)
                        overridePendingTransition(0, 0);
                    }
                }
                Mutables.previousPage === "FocusActivity" -> {
                    Intent(this, FocusActivity::class.java).also {
                        startActivity(it)
                        overridePendingTransition(0, 0);
                    }
                }
                Mutables.previousPage === "SetAlarmActivity" -> {
                    android.content.Intent(this, com.doc.droidysdroidsclock.SetAlarmActivity::class.java).also {
                        startActivity(it)
                        overridePendingTransition(0, 0);
                    }
                }
            }
        }


        val saveBtn: Button = findViewById(R.id.save_button)
        saveBtn.setOnClickListener {
            if (clockCheck.isChecked === true){
                val rl = (R.layout.activity_main) as ConstraintLayout
                rl.setBackgroundResource(newGradient)
            }
            if (alarmCheck.isChecked === true){
                val rl = (R.layout.activity_alarm) as ConstraintLayout
                rl.setBackgroundResource(newGradient)
                val rl2 = (R.layout.activity_set_alarm) as ConstraintLayout
                rl2.setBackgroundResource(newGradient)
            }
            if (stopwatchCheck.isChecked === true){
                val rl = (R.layout.activity_stopwatch) as ConstraintLayout
                rl.setBackgroundResource(newGradient)
            }
            if (timerCheck.isChecked === true){
                val rl = (R.layout.activity_timer) as ConstraintLayout
                rl.setBackgroundResource(newGradient)
            }
            if (focusCheck.isChecked === true){
                val rl = (R.layout.activity_focus) as ConstraintLayout
                rl.setBackgroundResource(newGradient)
            }

            when {
                Mutables.previousPage === "MainActivity" -> {
                    Intent(this, MainActivity::class.java).also {
                        startActivity(it)
                        overridePendingTransition(0, 0);
                    }
                }
                Mutables.previousPage === "AlarmActivity" -> {
                    Intent(this, AlarmActivity::class.java).also {
                        startActivity(it)
                        overridePendingTransition(0, 0);
                    }
                }
                Mutables.previousPage === "StopwatchActivity" -> {
                    Intent(this, StopwatchActivity::class.java).also {
                        startActivity(it)
                        overridePendingTransition(0, 0);
                    }
                }
                Mutables.previousPage === "TimerActivity" -> {
                    Intent(this, TimerActivity::class.java).also {
                        startActivity(it)
                        overridePendingTransition(0, 0);
                    }
                }
                Mutables.previousPage === "FocusActivity" -> {
                    Intent(this, FocusActivity::class.java).also {
                        startActivity(it)
                        overridePendingTransition(0, 0);
                    }
                }
                Mutables.previousPage === "SetAlarmActivity" -> {
                    Intent(this, SetAlarmActivity::class.java).also {
                        startActivity(it)
                        overridePendingTransition(0, 0);
                    }
                }
            }
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        TODO("Not yet implemented")
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}