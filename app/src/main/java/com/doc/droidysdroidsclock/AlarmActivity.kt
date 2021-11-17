package com.doc.droidysdroidsclock

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.Ringtone
import android.media.RingtoneManager
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

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


        val a1: ConstraintLayout = findViewById(R.id.alarm1)
        a1.setOnClickListener {
            Intent(this, SetAlarmActivity::class.java).also {
                startActivity(it)
            }
        }

        val a2: ConstraintLayout = findViewById(R.id.alarm2)
        a2.setOnClickListener {
            Intent(this, SetAlarmActivity::class.java).also {
                startActivity(it)
            }
        }

        val a3: ConstraintLayout = findViewById(R.id.alarm3)
        a3.setOnClickListener {
            Intent(this, SetAlarmActivity::class.java).also {
                startActivity(it)
            }
        }

        val a4: ConstraintLayout = findViewById(R.id.alarm4)
        a4.setOnClickListener {
            Intent(this, SetAlarmActivity::class.java).also {
                startActivity(it)
            }
        }

        val a5: ConstraintLayout = findViewById(R.id.alarm5)
        a5.setOnClickListener {
            Intent(this, SetAlarmActivity::class.java).also {
                startActivity(it)
            }
        }

        val ringtone: Ringtone = RingtoneManager.getRingtone(applicationContext, RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM))

        val a1Switch: Switch = findViewById(R.id.alarm1_switch)
        a1Switch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                val intent = Intent(applicationContext, AlarmNotification::class.java)
                val pendingIntent = PendingIntent.getBroadcast(applicationContext, 111, intent, 0)
                val alarmManager: AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
                alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 5000, pendingIntent)

                Toast.makeText(this, "Alarm Enabled", Toast.LENGTH_SHORT).show()
                ringtone.play()

            } else {
                ringtone.stop()
                Toast.makeText(this,"Disabled!", Toast.LENGTH_SHORT).show()
            }
        }

        val a2Switch: Switch = findViewById(R.id.alarm2_switch)
        a2Switch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                //ringtone.start()
                Toast.makeText(this, "Alarm Enabled", Toast.LENGTH_SHORT).show()
            } else {
                //ringtone.stop()
                Toast.makeText(this,"Alarm Disabled!", Toast.LENGTH_SHORT).show()
            }
        }

        val a3Switch: Switch = findViewById(R.id.alarm3_switch)
        a3Switch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                //ringtone.start()
                Toast.makeText(this, "Alarm Enabled", Toast.LENGTH_SHORT).show()
            } else {
                //ringtone.stop()
                Toast.makeText(this,"Disabled!", Toast.LENGTH_SHORT).show()
            }
        }

        val a4Switch: Switch = findViewById(R.id.alarm4_switch)
        a4Switch.setOnClickListener {
            Intent(this, SetAlarmActivity::class.java).also {
                startActivity(it)
            }
        }

        val a5Switch: Switch = findViewById(R.id.alarm5_switch)
        a5Switch.setOnClickListener {
            Intent(this, SetAlarmActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}