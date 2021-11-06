package com.doc.droidysdroidsclock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import com.doc.droidysdroidsclock.util.PrefUtil


class FocusActivity : AppCompatActivity() {

    enum class TimerState{
        Started, Paused, Stopped
    }

    /*
     * class attributes
     */
    private lateinit var timer: CountDownTimer
    private var timerLengthSeconds: Long = 0
    private var timerState = TimerState.Stopped
    private var secondsRemaining: Long = 0
    private lateinit var timeRemaining: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_focus)

        /*
        * set listeners for buttons in navigation bar
        */
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
        val stopwatchTab: Button = findViewById(R.id.stopwatch_button)
        stopwatchTab.setOnClickListener {
            Intent(this, StopwatchActivity::class.java).also {
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

        /*
         * get items in layout
         */
        val editWrk: EditText = findViewById(R.id.editWork) //textfield that holds name of work session
        val editShortBrkName: EditText = findViewById(R.id.editShortBrkName) // textfield that holds name of short break
        val editLongBreakName: EditText = findViewById(R.id.editLongBrkName) // textfield that holds name of long break
        val sessionLabel: TextView = findViewById(R.id.session_label) //text displaying if session is work or a break
        timeRemaining  = findViewById(R.id.time_remaining) //countdown area
        val pauseBtn: ImageButton = findViewById(R.id.pause_focus) //pause the timer; when pressed, it changes to a play for resume
        //val continueBtn: ImageButton = findViewById(R.id.continue_focus) //resume timer after pause
        val soundOnBtn: ImageButton = findViewById(R.id.sound_on_focus) //play music in background?
        val muteBtn: ImageButton = findViewById(R.id.focus_sound) //mute music?
        val cancelFocus: ImageButton = findViewById(R.id.cancel_focus) //cancel session
        val wrkTime: TextView = findViewById(R.id.time_for_work) //time for work session
        val shortBrkTime: TextView = findViewById(R.id.time_for_short_break) //time for short break
        val longBrkTime: TextView = findViewById(R.id.time_for_long_break) //time for long break
        val beginFocus: ImageButton = findViewById(R.id.begin_focus) //start focus


        //view 1
        sessionLabel.visibility = View.GONE
        timeRemaining.visibility = View.GONE
        pauseBtn.visibility = View.GONE
        soundOnBtn.visibility = View.GONE
        cancelFocus.visibility = View.GONE


        /*
         * set listener for begin button in view 1
         * so that when pressed
         ** we change to view 2 which is the countdown timer for the work session
         ** and supporting buttons
         */
        beginFocus.setOnClickListener {
            //view 2
            sessionLabel.visibility = View.VISIBLE
            timeRemaining.visibility = View.VISIBLE
            pauseBtn.visibility = View.VISIBLE
            soundOnBtn.visibility = View.VISIBLE
            startTimer()
            timerState = TimerState.Started

            //remove unnecessary items that were in view 1
            editWrk.visibility = View.GONE
            editShortBrkName.visibility = View.GONE
            editLongBreakName.visibility = View.GONE
            beginFocus.visibility = View.GONE
            muteBtn.visibility = View.GONE
            wrkTime.visibility = View.GONE
            shortBrkTime.visibility = View.GONE
            longBrkTime.visibility = View.GONE
        }

        /*
         * set listener for pause button in view 2
         * so that when pressed
         ** we change to view 3 which is the paused countdown timer for whatever session is in progress
         ** and supporting buttons
         */
        pauseBtn.setOnClickListener {
            //view 3
            cancelFocus.visibility = View.VISIBLE
            timer.cancel()
            timerState = TimerState.Paused

            //remove unnecessary items from view 2
            pauseBtn.visibility = View.GONE
            soundOnBtn.visibility = View.GONE
        }

        /*
         * set listener for cancel button in view 2 and 3
         * so that when pressed
         ** we change back to view 1
         */
        cancelFocus.setOnClickListener {
            //remove unnecessary items from view 2 or 3
            sessionLabel.visibility = View.GONE
            timeRemaining.visibility = View.GONE
            cancelFocus.visibility = View.GONE
            timer.cancel()
            onTimerFinished()
            timerState = TimerState.Stopped

            //view 1
            editWrk.visibility = View.VISIBLE
            editShortBrkName.visibility = View.VISIBLE
            editLongBreakName.visibility = View.VISIBLE
            beginFocus.visibility = View.VISIBLE
            muteBtn.visibility = View.VISIBLE
            wrkTime.visibility = View.VISIBLE
            shortBrkTime.visibility = View.VISIBLE
            longBrkTime.visibility = View.VISIBLE
        }

    }

    override fun onResume() {
        super.onResume()

        initTimer()
    }

    override fun onPause(){
        super.onPause()

        if (timerState == TimerState.Started){
            timer.cancel()
        }else if (timerState == TimerState.Paused){
            //b
        }

        PrefUtil.setPreviousTimeLengthSeconds(timerLengthSeconds, this)
        PrefUtil.setSecondsRemaining(secondsRemaining, this)
        PrefUtil.setTimerState(timerState,this)
    }

    fun initTimer(){
        timerState = PrefUtil.getTimerState(this)

        if (timerState == TimerState.Stopped){
            setNewTimerLength()
        }else{
            setPreviousTimerLength()
        }

        secondsRemaining = if (timerState== TimerState.Started || timerState== TimerState.Paused){
            PrefUtil.getSecondsRemaining(this)
         }else{
             timerLengthSeconds
        }

        if (timerState == TimerState.Started)
            startTimer()

        //updateButtons()
        updateCountdownUI()
    }

    private fun onTimerFinished(){
        timerState = TimerState.Stopped

        setNewTimerLength()

        //progress_countdown.progress = 0

        PrefUtil.setSecondsRemaining(timerLengthSeconds, this)
        secondsRemaining = timerLengthSeconds

        //updateButtons()
        updateCountdownUI()
    }

    private fun startTimer(){
        timerState = TimerState.Started

        timer = object : CountDownTimer(secondsRemaining*1000, 1000){
            override fun onFinish() = onTimerFinished()

            override fun onTick(millisUntilFinshed: Long) {
                secondsRemaining = millisUntilFinshed / 1000
                updateCountdownUI()
            }
        }.start()
    }

    private fun setNewTimerLength(){
        val lengthInMinutes = PrefUtil.getTimerLength(this)
        timerLengthSeconds = (lengthInMinutes * 60L)
        //progress_countdown.max = timerLengthSeconds.toInt()
    }

    private fun setPreviousTimerLength(){
        timerLengthSeconds = PrefUtil.getPreviousTimeLengthSeconds(this)
        //progress_countdown.max = timerLengthSeconds.toInt()
    }

    private fun updateCountdownUI(){
        val minutesUntilFinished = secondsRemaining/60
        val secondsInMinuteUntilFinished = secondsRemaining - minutesUntilFinished *60
        val secondsStr = secondsInMinuteUntilFinished.toString()
        timeRemaining.text = "$minutesUntilFinished:${
            if (secondsStr.length == 2) secondsStr
            else "0" + secondsStr
        }"
        //progress_countdown.progress = (timerLengthSeconds - secondsRemaining).toInt()
    }

    private fun updateButtons(){
        when (timerState){
            TimerState.Started -> {

            }
            TimerState.Paused -> {

            }
            TimerState.Stopped -> {

            }
        }
    }
}