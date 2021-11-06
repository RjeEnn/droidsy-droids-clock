package com.doc.droidysdroidsclock.util

import android.content.Context
import android.preference.PreferenceManager
import com.doc.droidysdroidsclock.FocusActivity
import java.security.AccessControlContext
import java.sql.Time

class PrefUtil {
    companion object{
        fun getTimerLength(context: Context): Int{
            return 1
        }

        private const val PREVIOUS_TIMER_LENGTH_SECONDS_ID = "com.doc.droidysdroidsclock.FocusActivity.previous_timer_length"

        fun getPreviousTimeLengthSeconds(context: Context): Long{
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getLong(PREVIOUS_TIMER_LENGTH_SECONDS_ID, 0)
        }

        fun setPreviousTimeLengthSeconds(seconds:Long, context: Context){
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putLong(PREVIOUS_TIMER_LENGTH_SECONDS_ID, seconds)
            editor.apply()
        }

        private const val TIMER_STATE_ID = "com.doc.droidysdroidsclock.FocusActivity.timer_state"

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

        private const val SECONDS_REMAINING_ID = "com.doc.droidysdroidsclock.FocusActivity.seconds_remaining"

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