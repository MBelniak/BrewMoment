package com.rubik.brewmoment.util

import android.content.Context
import android.preference.PreferenceManager
import com.rubik.brewmoment.ui.brew.BrewActivity

class PrefUtil {
    companion object {
        private const val TIMER_PAUSE_OFFSET = "timer_pause_offset"
        private const val TIMER_STATE = "timer_state"
        private const val CURRENT_STEP = "current_step"

        fun getTimerOffset(context: Context): Long {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getLong(TIMER_PAUSE_OFFSET, 0L)
        }

        fun setTimerOffset(offset: Long, context: Context) {
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putLong(TIMER_PAUSE_OFFSET, offset)
            editor.apply()
        }

        fun getTimerState(context: Context): BrewActivity.State {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            val index = preferences.getInt(TIMER_STATE, 0)
            return BrewActivity.State.values()[index]
        }

        fun setTimerState(state: BrewActivity.State, context: Context) {
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putInt(TIMER_STATE, state.ordinal)
            editor.apply()
        }

        fun getCurrentStep (context: Context): Int {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getInt(CURRENT_STEP, 0)
        }

        fun setCurrentStep(step: Int, context: Context) {
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putInt(CURRENT_STEP, step)
            editor.apply()
        }

    }
}