package com.doc.droidysdroidsclock

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.doc.droidysdroidsclock.util.Mutables

class SettingsActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("SetingsActivity", "created")
        setContentView(R.layout.activity_settings)

        val cl = findViewById(R.id.settings_page) as ConstraintLayout
        if (Mutables.customise === "gradient1") {
            cl.setBackgroundResource(R.drawable.gradient1)
        } else if (Mutables.customise === "gradient2") {
            cl.setBackgroundResource(R.drawable.gradient2)
        }else {
            cl.setBackgroundResource(R.drawable.gradient3)
        }

        var list_of_items = arrayOf("lync_ringtone5", "lync_ringtone6", "lync_videoadded")
        val dropdown: Spinner = findViewById(R.id.alarm_spinner)
        val aa = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, list_of_items)
        // Set layout to use when the list of choices appear
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Set Adapter to Spinner
        dropdown.adapter = aa
        dropdown.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?,selectedItemView: View,position: Int, id: Long
            ) {
                if (position === 0) {
                    Mutables.alarmTone = "lync_ringtone5"
                } else if (position === 1) {
                    Mutables.alarmTone = "lync_ringtone6"
                }else {
                    Mutables.alarmTone = "lync_videoadded"
                }
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // your code here
            }
        }

        val dropdown2: Spinner = findViewById(R.id.focus_spinner)
        val aa2 = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, list_of_items)
        // Set layout to use when the list of choices appear
        aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Set Adapter to Spinner
        dropdown2.adapter = aa2
        dropdown2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?,selectedItemView: View,position: Int, id: Long
            ) {
                if (position === 0) {
                    Mutables.focusTone = "lync_ringtone5"
                } else if (position === 1) {
                    Mutables.focusTone = "lync_ringtone6"
                }else {
                    Mutables.focusTone = "lync_videoadded"
                }
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // your code here
            }
        }


        val alarmCheck: CheckBox = findViewById(R.id.alarmCheckBox)
        val stopwatchCheck: CheckBox = findViewById(R.id.stopwatchCheckBox)
        val timerCheck: CheckBox = findViewById(R.id.timerCheckBox)
        val focusCheck: CheckBox = findViewById(R.id.focusCheckBox)
        val worldClockCheck: CheckBox = findViewById(R.id.world_clockCheckBox)


        val cancelBtn: Button = findViewById(R.id.cancel_button)
        cancelBtn.setOnClickListener {
            when {
                Mutables.previousPage === "MainActivity" -> {
                    Intent(this, MainActivity::class.java).also {
                        startActivity(it)
                        overridePendingTransition(0, 0)
                    }
                }
                Mutables.previousPage === "AlarmActivity" -> {
                    Intent(this, AlarmActivity::class.java).also {
                        startActivity(it)
                        overridePendingTransition(0, 0)
                    }
                }
                Mutables.previousPage === "StopwatchActivity" -> {
                    Intent(this, StopwatchActivity::class.java).also {
                        startActivity(it)
                        overridePendingTransition(0, 0)
                    }
                }
                Mutables.previousPage === "TimerActivity" -> {
                    Intent(this, TimerActivity::class.java).also {
                        startActivity(it)
                        overridePendingTransition(0, 0)
                    }
                }
                Mutables.previousPage === "FocusActivity" -> {
                    Intent(this, FocusActivity::class.java).also {
                        startActivity(it)
                        overridePendingTransition(0, 0)
                    }
                }
                Mutables.previousPage === "SetAlarmActivity" -> {
                    android.content.Intent(this, com.doc.droidysdroidsclock.SetAlarmActivity::class.java).also {
                        startActivity(it)
                        overridePendingTransition(0, 0)
                    }
                }
            }
        }


        val saveBtn: Button = findViewById(R.id.save_button)
        saveBtn.setOnClickListener {
            if (worldClockCheck.isChecked === true){
                Log.i("CustomiseActivity","clockChecked")
                Mutables.showWorldClock = true
            }else{
                Mutables.showWorldClock = false
            }
            if (alarmCheck.isChecked === true){
                Log.i("CustomiseActivity","alarmChecked")
                Mutables.showAlarm = true
            }else{
                Mutables.showAlarm = false
            }
            if (stopwatchCheck.isChecked === true){
                Log.i("CustomiseActivity","stopwatchChecked")
                Mutables.showStopwatch = true
            }else{
                Mutables.showStopwatch = false
            }
            if (timerCheck.isChecked === true){
                Log.i("CustomiseActivity","timerChecked")
                Mutables.showTimer = true
            }else{
                Mutables.showTimer = false
            }
            if (focusCheck.isChecked === true){
                Log.i("CustomiseActivity","focusChecked")
                Mutables.showFocus = true
            }else{
                Mutables.showFocus = false
            }

            when {
                Mutables.previousPage === "MainActivity" -> {
                    Intent(this, MainActivity::class.java).also {
                        startActivity(it)
                        overridePendingTransition(0, 0)
                    }
                }
                Mutables.previousPage === "AlarmActivity" -> {
                    Intent(this, AlarmActivity::class.java).also {
                        startActivity(it)
                        overridePendingTransition(0, 0)
                    }
                }
                Mutables.previousPage === "StopwatchActivity" -> {
                    Intent(this, StopwatchActivity::class.java).also {
                        startActivity(it)
                        overridePendingTransition(0, 0)
                    }
                }
                Mutables.previousPage === "TimerActivity" -> {
                    Intent(this, TimerActivity::class.java).also {
                        startActivity(it)
                        overridePendingTransition(0, 0)
                    }
                }
                Mutables.previousPage === "FocusActivity" -> {
                    Intent(this, FocusActivity::class.java).also {
                        startActivity(it)
                        overridePendingTransition(0, 0)
                    }
                }
                Mutables.previousPage === "SetAlarmActivity" -> {
                    Intent(this, SetAlarmActivity::class.java).also {
                        startActivity(it)
                        overridePendingTransition(0, 0)
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