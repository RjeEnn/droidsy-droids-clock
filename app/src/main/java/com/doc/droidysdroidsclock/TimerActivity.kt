package com.doc.droidysdroidsclock

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.NumberPicker
import android.widget.TextView
import com.doc.droidysdroidsclock.util.PrefUtil
import com.doc.droidysdroidsclock.util.TimerNotifUtil
import me.zhanghai.android.materialprogressbar.MaterialProgressBar
import java.util.*
import androidx.constraintlayout.widget.ConstraintLayout
import com.doc.droidysdroidsclock.util.Mutables

class TimerActivity : AppCompatActivity() {

    companion object {
        fun setAlarm(context: Context, elapsed: Long, remaining: Long): Long {
            val alertTime = (elapsed + remaining) * 1000
            val alrmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(context,TimerFinishedReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)

            alrmManager.setExact(AlarmManager.RTC_WAKEUP, alertTime, pendingIntent)
            PrefUtil.setAlarmTime(elapsed, context)

            return alertTime
        }

        fun removeAlarm(context: Context) {
            val alrmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(context,TimerFinishedReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
            alrmManager.cancel(pendingIntent)
            PrefUtil.setAlarmTime(0, context)
        }

        val elapsed: Long
            get() = Calendar.getInstance().timeInMillis / 1000
    }

    enum class TimerState {
        Running, Paused, Stopped
    }

    private lateinit var timer: CountDownTimer
    private var timerLength: Long = 0
    private var timeRemaining: Long = 0
    private var state = TimerState.Stopped

class TimerActivity : AppCompatActivity() {

    private var x1:Float = 0.0F
    private var x2:Float = 0.0F
    private var y1:Float = 0.0F
    private var y2:Float = 0.0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        val cl = findViewById(R.id.timer_page) as ConstraintLayout
        if (Mutables.timer === "gradient1") {
            cl.setBackgroundResource(R.drawable.gradient1)
        } else if (Mutables.timer === "gradient2") {
            cl.setBackgroundResource(R.drawable.gradient2)
        }else {
            cl.setBackgroundResource(R.drawable.gradient3)
        }

        // BUTTONS
        val clockTab: Button = findViewById(R.id.clock_button)
        clockTab.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                overridePendingTransition(0, 0)
            }
        }
        val alarmTab: Button = findViewById(R.id.alarm_button)
        alarmTab.setOnClickListener {
            Intent(this, AlarmActivity::class.java).also {
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
        val focusTab: Button = findViewById(R.id.focus_button)
        focusTab.setOnClickListener {
            Intent(this, FocusActivity::class.java).also {
                startActivity(it)
                overridePendingTransition(0, 0)
            }
        }
        val customiseBtn: ImageButton = findViewById(R.id.customise_button)
        customiseBtn.setOnClickListener {
            Mutables.previousPage = "TimerActivity"
            Intent(this, CustomiseActivity::class.java).also {
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
        fun startFocus(){
            Intent(this, FocusActivity::class.java).also {
                startActivity(it)
                overridePendingTransition(0, 0)
            }
        }

        val activity_timer = findViewById<View>(android.R.id.content).getRootView()
        activity_timer.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View, m: MotionEvent): Boolean {
                if (m.action === MotionEvent.ACTION_DOWN) {
                    x1 = m.x
                    y1 = m.y
                } else if (m.action === MotionEvent.ACTION_UP) {
                    x2 = m.x
                    y2 = m.y
                    if (x1 < x2) {
                        //swiped right
                        startStopwatch()
                    }else if (x1 > x2) {
                        //swiped left
                        startFocus()
                    }
                }
                return false
            }
        })

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


        val cancelBtn: ImageButton = findViewById(R.id.cancel_timer)
        val pauseBtn: ImageButton = findViewById(R.id.pause_timer)
        val timerBtn: ImageButton = findViewById(R.id.start_timer)
        val add15: ImageButton = findViewById(R.id.add15)
        val sub15: ImageButton = findViewById(R.id.sub15)
        val textDisplay: TextView = findViewById(R.id.timer_text)

        add15.visibility = View.GONE
        sub15.visibility = View.GONE
        cancelBtn.visibility = View.GONE
        pauseBtn.visibility = View.GONE
        textDisplay.visibility = View.GONE

        timerBtn.setOnClickListener {
            timerBtn.visibility = View.GONE

            add15.visibility = View.VISIBLE
            sub15.visibility = View.VISIBLE
            cancelBtn.visibility = View.VISIBLE
            pauseBtn.visibility = View.VISIBLE
            textDisplay.visibility = View.VISIBLE

            hrPicker.visibility = View.GONE
            minPicker.visibility = View.GONE
            secPicker.visibility = View.GONE

            val hrsSet = hrPicker.value
            val minsSet = minPicker.value
            val secsSet = secPicker.value

            val secsTotal = (hrsSet * 3600) + (minsSet * 60) + secsSet

            PrefUtil.setTimerLength(secsTotal, this)

            startTimer()
            newTimer()
            state = TimerState.Running

            add15.setOnClickListener {
                timeRemaining += 15
                PrefUtil.setTimeRemaining(timeRemaining, this)
                PrefUtil.setTimerLength(timerLength.toInt() + 15, this)
                updateTime()
            }

            sub15.setOnClickListener {
                if(timeRemaining >= 15) {
                    timeRemaining -= 15
                } else {
                    timeRemaining = 0
                }
                PrefUtil.setTimeRemaining(timeRemaining, this)
                PrefUtil.setTimerLength(timerLength.toInt() - 15, this)
                updateTime()
            }
        }

        pauseBtn.setOnClickListener {
            timerBtn.visibility = View.VISIBLE
            pauseBtn.visibility = View.GONE
            cancelBtn.visibility = View.GONE
            textDisplay.visibility = View.VISIBLE

            hrPicker.visibility = View.GONE
            minPicker.visibility = View.GONE
            secPicker.visibility = View.GONE

            timer.cancel()
            state = TimerState.Paused
        }

        cancelBtn.setOnClickListener {

            timer.cancel()
            endTimer()
        }

    }

    override fun onStart() {
        super.onStart()

        val cancelBtn: ImageButton = findViewById(R.id.cancel_timer)
        val pauseBtn: ImageButton = findViewById(R.id.pause_timer)
        val timerBtn: ImageButton = findViewById(R.id.start_timer)
        val add15: ImageButton = findViewById(R.id.add15)
        val sub15: ImageButton = findViewById(R.id.sub15)
        val textDisplay: TextView = findViewById(R.id.timer_text)
        val hrPicker: NumberPicker = findViewById(R.id.timer_hr)
        val minPicker: NumberPicker = findViewById(R.id.timer_min)
        val secPicker: NumberPicker = findViewById(R.id.timer_sec)

        state = PrefUtil.getState(this)

        when (state) {
            TimerState.Running -> {
                cancelBtn.visibility = View.VISIBLE
                pauseBtn.visibility = View.VISIBLE
                timerBtn.visibility = View.GONE
                add15.visibility = View.VISIBLE
                sub15.visibility = View.VISIBLE
                textDisplay.visibility = View.VISIBLE
                hrPicker.visibility = View.GONE
                minPicker.visibility = View.GONE
                secPicker.visibility = View.GONE
            }
            TimerState.Paused -> {
                cancelBtn.visibility = View.GONE
                pauseBtn.visibility = View.GONE
                timerBtn.visibility = View.VISIBLE
                add15.visibility = View.VISIBLE
                sub15.visibility = View.VISIBLE
                textDisplay.visibility = View.VISIBLE
                hrPicker.visibility = View.GONE
                minPicker.visibility = View.GONE
                secPicker.visibility = View.GONE
            }
            TimerState.Stopped -> {
                cancelBtn.visibility = View.GONE
                pauseBtn.visibility = View.GONE
                timerBtn.visibility = View.VISIBLE
                add15.visibility = View.GONE
                sub15.visibility = View.GONE
                textDisplay.visibility = View.GONE
                hrPicker.visibility = View.VISIBLE
                minPicker.visibility = View.VISIBLE
                secPicker.visibility = View.VISIBLE
            }
        }
    }

    override fun onResume() {
        super.onResume()

        initTimer()

        removeAlarm(this)

        TimerNotifUtil.hideNotif(this)

        val cancelBtn: ImageButton = findViewById(R.id.cancel_timer)
        val pauseBtn: ImageButton = findViewById(R.id.pause_timer)
        val timerBtn: ImageButton = findViewById(R.id.start_timer)
        val add15: ImageButton = findViewById(R.id.add15)
        val sub15: ImageButton = findViewById(R.id.sub15)
        val textDisplay: TextView = findViewById(R.id.timer_text)
        val hrPicker: NumberPicker = findViewById(R.id.timer_hr)
        val minPicker: NumberPicker = findViewById(R.id.timer_min)
        val secPicker: NumberPicker = findViewById(R.id.timer_sec)

        state = PrefUtil.getState(this)

        when (state) {
            TimerState.Running -> {
                cancelBtn.visibility = View.VISIBLE
                pauseBtn.visibility = View.VISIBLE
                timerBtn.visibility = View.GONE
                add15.visibility = View.VISIBLE
                sub15.visibility = View.VISIBLE
                textDisplay.visibility = View.VISIBLE
                hrPicker.visibility = View.GONE
                minPicker.visibility = View.GONE
                secPicker.visibility = View.GONE
            }
            TimerState.Paused -> {
                cancelBtn.visibility = View.GONE
                pauseBtn.visibility = View.GONE
                timerBtn.visibility = View.VISIBLE
                add15.visibility = View.VISIBLE
                sub15.visibility = View.VISIBLE
                textDisplay.visibility = View.VISIBLE
                hrPicker.visibility = View.GONE
                minPicker.visibility = View.GONE
                secPicker.visibility = View.GONE
            }
            TimerState.Stopped -> {
                cancelBtn.visibility = View.GONE
                pauseBtn.visibility = View.GONE
                timerBtn.visibility = View.VISIBLE
                add15.visibility = View.GONE
                sub15.visibility = View.GONE
                textDisplay.visibility = View.GONE
                hrPicker.visibility = View.VISIBLE
                minPicker.visibility = View.VISIBLE
                secPicker.visibility = View.VISIBLE
            }
        }
    }

    override fun onPause() {
        super.onPause()

        if(state == TimerState.Running) {
            timer.cancel()
            val alertTime = setAlarm(this, elapsed, timeRemaining)
            TimerNotifUtil.showTimerOngoing(this, alertTime)
        } else if(state == TimerState.Paused) {
            TimerNotifUtil.showTimerPaused(this)
        }

        PrefUtil.setLenInSec(timerLength, this)
        PrefUtil.setTimeRemaining(timeRemaining, this)
        PrefUtil.setState(state, this)
    }

    private fun initTimer() {
        state = PrefUtil.getState(this)
        if(state == TimerState.Stopped) {
            newTimer()
        } else {
            setLen()
        }

        timeRemaining = if(state == TimerState.Running || state == TimerState.Paused) {
            PrefUtil.getTimeRemaining(this)
        } else {
            timerLength
        }

        val alarmTime = PrefUtil.getAlarmTime(this)
        if(alarmTime > 0) {
            timeRemaining -= elapsed - alarmTime
        }

        if (timeRemaining <= 0)
            endTimer()
        else if (state == TimerState.Running)
            startTimer()

        updateTime()
    }

    private fun endTimer() {
        val cancelBtn: ImageButton = findViewById(R.id.cancel_timer)
        val pauseBtn: ImageButton = findViewById(R.id.pause_timer)
        val timerBtn: ImageButton = findViewById(R.id.start_timer)
        val add15: ImageButton = findViewById(R.id.add15)
        val sub15: ImageButton = findViewById(R.id.sub15)
        val textDisplay: TextView = findViewById(R.id.timer_text)
        val hrPicker: NumberPicker = findViewById(R.id.timer_hr)
        val minPicker: NumberPicker = findViewById(R.id.timer_min)
        val secPicker: NumberPicker = findViewById(R.id.timer_sec)

        state = TimerState.Stopped

        timerBtn.visibility = View.VISIBLE

        add15.visibility = View.GONE
        sub15.visibility = View.GONE
        cancelBtn.visibility = View.GONE
        pauseBtn.visibility = View.GONE
        textDisplay.visibility = View.GONE

        hrPicker.visibility = View.VISIBLE
        minPicker.visibility = View.VISIBLE
        secPicker.visibility = View.VISIBLE

        newTimer()

        val progressCD: MaterialProgressBar = findViewById(R.id.materialProgressBar)
        progressCD.progress = 0
        PrefUtil.setTimeRemaining(timerLength, this)
        timeRemaining = timerLength

        updateTime()
    }

    private fun startTimer() {
        if(state == TimerState.Stopped) {
            timeRemaining = PrefUtil.getTimerLength(this).toLong()
        }
        state = TimerState.Running

        timer = object: CountDownTimer(timeRemaining * 1000, 1000) {
            override fun onFinish() = endTimer()
            override fun onTick(p0: Long) {
                timeRemaining = p0 / 1000
                updateTime()
            }
        }.start()
    }

    private fun newTimer() {
        val progressCD: MaterialProgressBar = findViewById(R.id.materialProgressBar)
        val len = PrefUtil.getTimerLength(this)
        timerLength = len.toLong()
        progressCD.max = timerLength.toInt()
    }

    private fun setLen() {
        val progressCD: MaterialProgressBar = findViewById(R.id.materialProgressBar)
        timerLength = PrefUtil.getLenInSec(this)
        progressCD.max = timerLength.toInt()
    }

    private fun updateTime() {
        val timerDisplay: TextView = findViewById(R.id.timer_text)
        val progressCD: MaterialProgressBar = findViewById(R.id.materialProgressBar)
        val hrs = timeRemaining.toInt() % 86400 / 3600
        val min = timeRemaining.toInt() % 86400 % 3600 / 60
        val sec = timeRemaining.toInt() % 86400 % 3600 % 60

        timerDisplay.text = String.format("%02d:%02d:%02d", hrs, min, sec)
        progressCD.progress = (timerLength - timeRemaining).toInt()
    }
}