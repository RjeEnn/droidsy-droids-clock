package com.doc.droidysdroidsclock

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextClock
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import java.text.DateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val alarmTab: Button = findViewById(R.id.button2)
        alarmTab.setOnClickListener {
            Intent(this, AlarmActivity::class.java).also {
                startActivity(it)
            }
        }

        val calendar: Calendar = Calendar.getInstance()
        val currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.time)
        val textViewDate: TextView = findViewById(R.id.dateText)
        textViewDate.text = currentDate.toString().lowercase()

        val c: Context = applicationContext
        val timeClock: TextClock = findViewById(R.id.digitalClock)
        val typeface = ResourcesCompat.getFont(c,R.font.comfortaa_bold)
        timeClock.typeface = typeface
    }
}