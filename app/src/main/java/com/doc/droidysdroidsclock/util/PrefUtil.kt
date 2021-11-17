package com.doc.droidysdroidsclock.util

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.doc.droidysdroidsclock.TimerActivity
import android.preference.PreferenceManager
import android.util.Log
import android.widget.EditText
import android.widget.NumberPicker
import com.doc.droidysdroidsclock.FocusActivity
import java.security.AccessControlContext
import java.sql.Time

class PrefUtil {
    companion object {

        private const val TIMER_SET_LEN = "TimerSetLen"

        fun getTimeLength(context: Context): Int {
            val prefs: SharedPreferences = context.getSharedPreferences(TIMER_SET_LEN, MODE_PRIVATE)
            return prefs.getInt(TIMER_SET_LEN, 0)
        }

        fun setTimerLength(secs: Int, context: Context) {
            val prefs: SharedPreferences = context.getSharedPreferences(TIMER_SET_LEN, MODE_PRIVATE)
            val editor: SharedPreferences.Editor = prefs.edit()
            editor.putInt(TIMER_SET_LEN, secs)
            editor.apply()
        }

        private const val TIMER_LEN_SEC = "timerLength"
        private const val TIMER_STATE = "timerState"
        private const val TIME_REMAINING = "timeRemaining"
        private const val ALARM_TIME = "alarmTime"
        private const val PREVIOUS_TIMER_LENGTH_SECONDS_ID = "com.doc.droidysdroidsclock.FocusActivity.previous_timer_length"
        private const val TIMER_STATE_ID = "com.doc.droidysdroidsclock.FocusActivity.timer_state"
        private const val SECONDS_REMAINING_ID = "com.doc.droidysdroidsclock.FocusActivity.seconds_remaining"

        fun getLenInSec(context: Context): Long {
            val prefs: SharedPreferences = context.getSharedPreferences(TIMER_LEN_SEC, MODE_PRIVATE)
            return prefs.getLong(TIMER_LEN_SEC, 0)
        }

        fun setLenInSec(secs: Long, context: Context) {
            val prefs: SharedPreferences = context.getSharedPreferences(TIMER_LEN_SEC, MODE_PRIVATE)
            val editor: SharedPreferences.Editor = prefs.edit()
            editor.putLong(TIMER_LEN_SEC, secs)
            editor.apply()
        }

        fun getState(context: Context): TimerActivity.TimerState {
            val prefs: SharedPreferences = context.getSharedPreferences(TIMER_LEN_SEC, MODE_PRIVATE)
            val index = prefs.getInt(TIMER_STATE, 2)
            return TimerActivity.TimerState.values()[index]
        }

        fun setState(state: TimerActivity.TimerState, context: Context) {
            val prefs: SharedPreferences = context.getSharedPreferences(TIMER_LEN_SEC, MODE_PRIVATE)
            val editor: SharedPreferences.Editor = prefs.edit()
            val index = state.ordinal
            editor.putInt(TIMER_STATE, index)
            editor.apply()
        }

        fun getTimeRemaining(context: Context): Long {
            val prefs: SharedPreferences = context.getSharedPreferences(TIME_REMAINING, MODE_PRIVATE)
            return prefs.getLong(TIME_REMAINING, 0)
        }

        fun setTimeRemaining(secs: Long, context: Context) {
            val prefs: SharedPreferences = context.getSharedPreferences(TIME_REMAINING, MODE_PRIVATE)
            val editor: SharedPreferences.Editor = prefs.edit()
            editor.putLong(TIME_REMAINING, secs)
            editor.apply()
        }

        fun getAlarmTime(context: Context): Long {
            val prefs: SharedPreferences = context.getSharedPreferences(ALARM_TIME, MODE_PRIVATE)
            return prefs.getLong(ALARM_TIME, 0)
        }

        fun setAlarmTime(time: Long, context: Context) {
            val prefs: SharedPreferences = context.getSharedPreferences(ALARM_TIME, MODE_PRIVATE)
            val editor: SharedPreferences.Editor = prefs.edit()
            editor.putLong(ALARM_TIME, time)
        }

        fun getTimerLength(context: Context, min: Int, sec: Int): Double{
            return min.toDouble() + sec/60.0
        }

        fun getPreviousTimeLengthSeconds(context: Context): Long{
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getLong(PREVIOUS_TIMER_LENGTH_SECONDS_ID, 0)
        }

        fun setPreviousTimeLengthSeconds(seconds:Long, context: Context){
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putLong(PREVIOUS_TIMER_LENGTH_SECONDS_ID, seconds)
            editor.apply()
        }

        fun getTimerState(context: Context): FocusActivity.TimerState{
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            val ordinal = preferences.getInt(TIMER_STATE_ID, 0)
            return FocusActivity.TimerState.values()[ordinal]
        }

        fun setTimerState(state: FocusActivity.TimerState, context: Context){
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            val ordinal = state.ordinal
            editor.putInt(TIMER_STATE_ID, ordinal)
            editor.apply()
        }

        fun getSecondsRemaining(context: Context): Long{
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getLong(SECONDS_REMAINING_ID, 0)
        }

        fun setSecondsRemaining(seconds:Long, context: Context){
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putLong(SECONDS_REMAINING_ID, seconds)
            editor.apply()
        }
    }
}