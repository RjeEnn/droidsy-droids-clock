package com.doc.droidysdroidsclock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import com.doc.droidysdroidsclock.util.Mutables
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
    private lateinit var sessionLabel: TextView
    private var wrkMin: Int = 0
    private var wrkSec: Int = 0
    private var longMin: Int = 0
    private var longSec: Int = 0
    private var shortMin: Int = 0
    private var shortSec: Int = 0
    private var sesh: String = "work"
    private var count: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("FocusActivity","created")
        setContentView(R.layout.activity_focus)

        val cl = findViewById(R.id.focus_page) as ConstraintLayout;
        if (Mutables.focus === "gradient1") {
            cl.setBackgroundResource(R.drawable.gradient1);
        } else if (Mutables.focus === "gradient2") {
            cl.setBackgroundResource(R.drawable.gradient2);
        }else {
            cl.setBackgroundResource(R.drawable.gradient3);
        }

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

        val customiseBtn: ImageButton = findViewById(R.id.customise_button)
        customiseBtn.setOnClickListener {
            Mutables.previousPage = "FocusActivity";
            Intent(this, CustomiseActivity::class.java).also {
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
        sessionLabel = findViewById(R.id.session_label) //text displaying if session is work or a break
        timeRemaining  = findViewById(R.id.time_remaining) //countdown area
        val pauseBtn: ImageButton = findViewById(R.id.pause_focus) //pause the timer; when pressed, it changes to a play for resume
        val continueBtn: ImageButton = findViewById(R.id.continue_focus) //resume timer after pause
        val soundOnBtn: ImageButton = findViewById(R.id.sound_on_focus) //play music in background?
        val muteBtn: ImageButton = findViewById(R.id.focus_sound) //mute music?
        val cancelFocus: ImageButton = findViewById(R.id.cancel_focus) //cancel session
        val beginFocus: ImageButton = findViewById(R.id.begin_focus) //start focus
        val editWrkMin: NumberPicker = findViewById(R.id.wrk_min) //number of minutes for work countdowm
        val editWrkSec: NumberPicker = findViewById(R.id.wrk_sec) //number of minutes for work countdowm
        val editLongMin: NumberPicker = findViewById(R.id.long_min) //number of minutes for work countdowm
        val editLongSec: NumberPicker = findViewById(R.id.long_sec) //number of minutes for work countdowm
        val editShortMin: NumberPicker = findViewById(R.id.short_min) //number of minutes for work countdowm
        val editShortSec: NumberPicker = findViewById(R.id.short_sec) //number of minutes for work countdowm
        val moreBtn: ImageButton = findViewById(R.id.more_button) //settings

        if (editWrkMin != null) {
            editWrkMin.minValue = 0
            editWrkMin.maxValue = 59
            editWrkMin.wrapSelectorWheel = true
        }else{Log.i("FocusActivity","editWrkMin not created")}

        if (editWrkSec != null) {
            editWrkSec.minValue = 0
            editWrkSec.maxValue = 59
            editWrkSec.wrapSelectorWheel = true
        }else{Log.i("FocusActivity","editWrkSec not created")}

        if (editLongMin != null) {
            editLongMin.minValue = 0
            editLongMin.maxValue = 59
            editLongMin.wrapSelectorWheel = true
        }else{Log.i("FocusActivity","editLongMin not created")}

        if (editLongSec != null) {
            editLongSec.minValue = 0
            editLongSec.maxValue = 59
            editLongSec.wrapSelectorWheel = true
        }else{Log.i("FocusActivity","editLongSec not created")}

        if (editShortMin != null) {
            editShortMin.minValue = 0
            editShortMin.maxValue = 59
            editShortSec.wrapSelectorWheel = true
        }else{Log.i("FocusActivity","editShortMin not created")}

        if (editShortSec != null) {
            editShortSec.minValue = 0
            editShortSec.maxValue = 59
            editShortSec.wrapSelectorWheel = true
        }else{Log.i("FocusActivity","editShortSec not created")}


        //view 1
        sessionLabel.visibility = View.GONE
        timeRemaining.visibility = View.GONE
        pauseBtn.visibility = View.GONE
        continueBtn.visibility = View.GONE
        soundOnBtn.visibility = View.GONE
        cancelFocus.visibility = View.GONE


        editWrk.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                Mutables.work = s.toString()
            }
        })

        editLongBreakName.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                Mutables.brk2 = s.toString()
            }
        })

        editShortBrkName.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                Mutables.brk1 = s.toString()
            }
        })

        /*
         * set listener for begin button in view 1
         * so that when pressed
         ** we change to view 2 which is the countdown timer for the work session
         ** and supporting buttons
         */
        beginFocus.setOnClickListener {
            Log.i("FocusActivity","begin was clicked")
            /*Log.i("FocusActivity", "wrkMin: ${editWrkMin.value}")
            Log.i("FocusActivity", "wrkSec: ${editWrkSec.value}")
            Log.i("FocusActivity", "longMin: ${editLongMin.value}")
            Log.i("FocusActivity", "longSec: ${editLongSec.value}")
            Log.i("FocusActivity", "shortMin: ${editShortMin.value}")
            Log.i("FocusActivity", "shortSec: ${editShortSec.value}")*/

            if ((editWrkMin.value != 0) or (editWrkSec.value != 0 )) {
                wrkMin = editWrkMin.value
                wrkSec = editWrkSec.value
                longMin = editLongMin.value
                longSec = editLongSec.value
                shortMin = editShortMin.value
                shortSec  = editShortSec.value

                Log.i("FocusActivity","wrkMin $wrkMin")
                Log.i("FocusActivity","wrkSec $wrkSec")
                Log.i("FocusActivity","longMin $longMin")
                Log.i("FocusActivity","longSec $longSec")
                Log.i("FocusActivity","shortMin $shortMin")
                Log.i("FocusActivity","shortSec $shortSec")


                //view 2
                sessionLabel.visibility = View.VISIBLE
                timeRemaining.visibility = View.VISIBLE
                pauseBtn.visibility = View.VISIBLE
                soundOnBtn.visibility = View.VISIBLE
                cancelFocus.visibility = View.VISIBLE
                setNewTimerLength()
                secondsRemaining = timerLengthSeconds
                startTimer()
                timerState = TimerState.Started

                //remove unnecessary items that were in view 1
                editWrk.visibility = View.GONE
                editShortBrkName.visibility = View.GONE
                editLongBreakName.visibility = View.GONE
                beginFocus.visibility = View.GONE
                muteBtn.visibility = View.GONE
                editWrkMin.visibility = View.GONE
                editWrkSec.visibility = View.GONE
                editLongMin.visibility = View.GONE
                editLongSec.visibility = View.GONE
                editShortMin.visibility = View.GONE
                editShortSec.visibility = View.GONE
                customiseBtn.visibility = View.GONE
                moreBtn.visibility = View.GONE
            }
        }

        /*
         * set listener for pause button in view 2
         * so that when pressed
         ** we change to view 3 which is the paused countdown timer for whatever session is in progress
         ** and supporting buttons
         */
        pauseBtn.setOnClickListener {
            Log.i("FocusActivity","pause clicked")
            //view 3
            continueBtn.visibility = View.VISIBLE
            timer.cancel()
            timerState = TimerState.Paused

            //remove unnecessary items from view 2
            pauseBtn.visibility = View.GONE
            soundOnBtn.visibility = View.GONE
        }

        /*
         * set listener for continue button in view 3
         * so that when pressed
         ** we change to view 2 which is the countdown timer for whatever session is in progress continued
         ** and supporting buttons
         */
        continueBtn.setOnClickListener {
            Log.i("FocusActivity","continue clicked")
            //view 3
            startTimer()
            pauseBtn.visibility = View.VISIBLE
            soundOnBtn.visibility = View.VISIBLE


            //remove unnecessary items from view 3
            continueBtn.visibility = View.GONE
        }

        /*
         * set listener for cancel button in view 2 and 3
         * so that when pressed
         ** we change back to view 1
         */
        cancelFocus.setOnClickListener {
            Log.i("FocusActivity","cancel clicked")
            //remove unnecessary items from view 2 or 3
            sessionLabel.visibility = View.GONE
            timeRemaining.visibility = View.GONE
            cancelFocus.visibility = View.GONE
            continueBtn.visibility = View.GONE
            pauseBtn.visibility = View.GONE
            soundOnBtn.visibility = View.GONE
            timer.cancel()
            onTimerFinished(false)
            timerState = TimerState.Stopped

            //view 1
            editWrk.visibility = View.VISIBLE
            editShortBrkName.visibility = View.VISIBLE
            editLongBreakName.visibility = View.VISIBLE
            beginFocus.visibility = View.VISIBLE
            muteBtn.visibility = View.VISIBLE
            editWrkMin.visibility = View.VISIBLE
            editWrkSec.visibility = View.VISIBLE
            editLongMin.visibility = View.VISIBLE
            editLongSec.visibility = View.VISIBLE
            editShortMin.visibility = View.VISIBLE
            editShortSec.visibility = View.VISIBLE
            customiseBtn.visibility = View.VISIBLE
            moreBtn.visibility = View.VISIBLE
        }

    }

    override fun onResume() {
        Log.i("FocusActivity","onResume")
        super.onResume()

        initTimer()
    }

    override fun onPause(){
        Log.i("FocusActivity","onPause")
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
        Log.i("FocusActivity","initTimer called")
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

        //updateButtons()
        updateCountdownUI()
    }

    private fun onTimerFinished(onOwn: Boolean){
        Log.i("FocusActivity","onTimerFisnished called")
        timerState = TimerState.Stopped
        if (onOwn) {
            Log.i("FocusActivity","called by onFinish")
            if (sesh.equals("work")) {
                Log.i("FocusActivity","and sesh is work")
                count = count + 1
                if (count < 4) {
                    Log.i("FocusActivity","and short break is next")
                    sesh = "short"
                    sessionLabel.text = Mutables.brk1
                } else {
                    Log.i("FocusActivity","and long break is next")
                    sesh = "long"
                    sessionLabel.text = Mutables.brk2
                }
            } else {
                Log.i("FocusActivity","and sesh is a break")
                sesh = "work"
                sessionLabel.text = Mutables.work
            }
        }else{
            Log.i("FocusActivity","by cancel button")
            sesh = "work"
            sessionLabel.text = Mutables.work
        }
        setNewTimerLength()
        //progress_countdown.progress = 0

        PrefUtil.setSecondsRemaining(timerLengthSeconds, this)
        secondsRemaining = timerLengthSeconds
        Log.i("FocusActivity","secondsRemaining changed in onTimerFinished: $secondsRemaining")

        //updateButtons()
        updateCountdownUI()
        if (onOwn) {
            Log.i("FocusActivity","next session timer started")
            startTimer()
        }
    }

    private fun startTimer(){
        timerState = TimerState.Started
        Log.i("FocusActivity","start timer called")

        timer = object : CountDownTimer(secondsRemaining*1000, 1000){
            override fun onFinish() { onTimerFinished(true) }

            override fun onTick(millisUntilFinshed: Long) {
                secondsRemaining = millisUntilFinshed / 1000
                updateCountdownUI()
            }
        }.start()
    }

    private fun setNewTimerLength(){
        Log.i("FocusActivity","setNewTimerLength called")
        val lengthInMinutes: Double
        if (sesh.equals("work")){
            Log.i("FocusActivity","sesh is work in setNewTimerLength function")
            lengthInMinutes = com.doc.droidysdroidsclock.util.PrefUtil.getTimerLength(this, wrkMin, wrkSec)
        } else if (sesh.equals("long")){
            Log.i("FocusActivity","sesh is long in setNewTimerLength function")
            lengthInMinutes = com.doc.droidysdroidsclock.util.PrefUtil.getTimerLength(this, longMin, longSec)
        } else {
            Log.i("FocusActivity","sesh is short in setNetTimerLength function")
            lengthInMinutes = com.doc.droidysdroidsclock.util.PrefUtil.getTimerLength(this, shortMin, shortSec)
            }
        timerLengthSeconds = (lengthInMinutes * 60).toLong()
        //progress_countdown.max = timerLengthSeconds.toInt()
    }

    private fun setPreviousTimerLength(){
        Log.i("FocusActivity","setPreviousTimerLength called")
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