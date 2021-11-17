package com.doc.droidysdroidsclock.util

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.doc.droidysdroidsclock.TimerActivity

class PrefUtil {
    companion object {

        private const val TIMER_SET_LEN = "TimerSetLen"

        fun getTimerLength(context: Context): Int {
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
            editor.apply()
        }
    }
}