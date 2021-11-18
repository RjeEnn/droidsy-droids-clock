package com.doc.droidysdroidsclock

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.Ringtone
import android.media.RingtoneManager
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.doc.droidysdroidsclock.util.Mutables
import android.widget.Switch
import android.widget.Toast

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
        } else if (Mutables.alarm === "gradient3") {
            cl.setBackgroundResource(R.drawable.gradient3)
        } else if (Mutables.alarm === "gradient4") {
            cl.setBackgroundResource(R.drawable.gradient4)
        } else if (Mutables.alarm === "gradient5") {
            cl.setBackgroundResource(R.drawable.gradient5)
        } else if (Mutables.alarm === "gradient6") {
            cl.setBackgroundResource(R.drawable.gradient6)
        } else if (Mutables.alarm === "gradient7") {
            cl.setBackgroundResource(R.drawable.gradient7)
        }else {
            cl.setBackgroundResource(R.drawable.gradient8)
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

        val customiseBtn: ImageButton = findViewById(R.id.customise_button)
        customiseBtn.setOnClickListener {
            Mutables.previousPage = "AlarmActivity"
            Intent(this, CustomiseActivity::class.java).also {
                startActivity(it)
                overridePendingTransition(0, 0)
            }
        }

        val settingsBtn: ImageButton = findViewById(R.id.settings_button)
        settingsBtn.setOnClickListener {
            Mutables.previousPage = "AlarmActivity"
            Intent(this, SettingsActivity::class.java).also {
                startActivity(it)
                overridePendingTransition(0, 0)
            }
        }

        val worldClockTab: Button = findViewById(R.id.world_clock_button)
        worldClockTab.setOnClickListener {
            Intent(this, WorldClockActivity::class.java).also {
                startActivity(it)
                overridePendingTransition(0, 0)
            }
        }

        if (!Mutables.showStopwatch) { stopwatchTab.visibility = View.GONE }
        if (!Mutables.showTimer) { timerTab.visibility = View.GONE }
        if (!Mutables.showFocus) { focusTab.visibility = View.GONE }
        if (!Mutables.showWorldClock) { worldClockTab.visibility = View.GONE }

        fun startMainActivity(){
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                overridePendingTransition(0, 0)
            }
        }
        fun startStopwatchActivity(){
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
                        startMainActivity()
                    }else if (x1 > x2) {
                        //swiped left
                        startStopwatchActivity()
                    }
                }
                return false
            }
        })
    }
}