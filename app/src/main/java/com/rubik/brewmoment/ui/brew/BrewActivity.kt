package com.rubik.brewmoment.ui.brew

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.rubik.brewmoment.R
import com.rubik.brewmoment.model.data.CommonRecipesData
import com.rubik.brewmoment.model.data.Recipe
import com.rubik.brewmoment.model.data.RecipesDAO
import com.rubik.brewmoment.ui.recipes.chosen_recipe.RecipeDetailsActivity
import com.rubik.brewmoment.ui.results.BrewResultActivity
import com.rubik.brewmoment.util.PrefUtil
import kotlinx.android.synthetic.main.activity_brew.*

class BrewActivity : AppCompatActivity() {
    private lateinit var recipe: Recipe
    private var pauseOffset = 0L
    private var currentStep = 0
    enum class State {
        Stopped, Paused, Running
    }
    private var stopwatchState = State.Stopped

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_brew)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.title = resources.getString(R.string.brew_title)
        initRecipe(intent)
        PrefUtil.setTimerOffset(0, this)
        PrefUtil.setTimerState(State.Stopped, this)
        PrefUtil.setCurrentStep(0, this)
        start_pause_button.setOnClickListener {
            when (stopwatchState) {
                State.Stopped -> {
                    startStopwatch()
                }
                State.Running -> {
                    pauseStopwatch()
                }
                State.Paused -> {
                    startStopwatch()
                }
            }
        }
        stop_button.setOnClickListener {
            resetStopwatch()
        }

        next.setOnClickListener{
            when (currentStep < recipe.steps.size - 1) {
                true -> nextStep()
                false -> finishBrewing()
            }
        }

        back.setOnClickListener{
            if (back.isVisible)
                previousStep()
        }
    }

    private fun initRecipe(intent: Intent) {
        val key: String? = intent.extras?.getString("RecipeKey")
        val isDefault: Boolean? = intent.extras?.getBoolean("IsDefault")
        if (key != null)
            recipe = if (isDefault != null)
                CommonRecipesData.getByKey(key)
            else
                RecipesDAO.getByKey(key)
        else
            CommonRecipesData.getDefaultRecipe()
    }

    private fun nextStep() {
        currentStep++
        updateInstructions()
    }

    private fun previousStep() {
        currentStep--
        updateInstructions()
    }

    private fun finishBrewing() {
        val intent = Intent(this, BrewResultActivity::class.java)
        val bundle = Bundle()
        val seconds: Int = ((SystemClock.elapsedRealtime() - stopwatch_chronometer.base) / 1000).toInt()
        bundle.putInt("Minutes", seconds/60)
        bundle.putInt("Seconds", seconds%60)
        bundle.putString("RecipeKey", recipe.key)
        bundle.putBoolean("IsDefault", recipe.isDefault)
        intent.putExtras(bundle)
        startActivity(intent)
        finish()
    }

    private fun updateInstructions() {
        brew_instruction.text = recipe.steps[currentStep].description
        brew_authors_tip.text = recipe.steps[currentStep].authorsTips
        back.text = getString(R.string.back)
        back.visibility = if (currentStep == 0) View.INVISIBLE else View.VISIBLE
        next.text = if (currentStep == recipe.steps.size - 1) getString(R.string.finish) else getString(R.string.next)
    }

    private fun startStopwatch() {
        stopwatchState = State.Running
        stopwatch_chronometer.base = SystemClock.elapsedRealtime() - pauseOffset
        stopwatch_chronometer.start()
        start_pause_button.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_pause))
    }

    private fun pauseStopwatch() {
        stopwatchState = State.Paused
        stopwatch_chronometer.stop()
        pauseOffset = SystemClock.elapsedRealtime() - stopwatch_chronometer.base
        start_pause_button.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_play_arrow))
    }

    private fun resetStopwatch() {
        stopwatchState = State.Stopped
        stopwatch_chronometer.base = SystemClock.elapsedRealtime()
        start_pause_button.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_play_arrow))
        pauseOffset = 0
    }

    override fun onResume() {
        super.onResume()
        stopwatchResume()
        updateInstructions()
    }

    override fun onPause() {
        super.onPause()

        if (stopwatchState == State.Running) {
            pauseStopwatch()
        }
        PrefUtil.setTimerOffset(pauseOffset, this)
        PrefUtil.setTimerState(stopwatchState, this)
        PrefUtil.setCurrentStep(currentStep, this)
    }

    private fun stopwatchResume() {
        stopwatchState = PrefUtil.getTimerState(this)
        when (stopwatchState) {
            State.Stopped -> {
                resetStopwatch()
            }
            State.Paused -> {
                pauseOffset = PrefUtil.getTimerOffset(this)
                stopwatch_chronometer.base = SystemClock.elapsedRealtime() - pauseOffset
                start_pause_button.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_play_arrow))
            }
            State.Running -> {
                pauseOffset = PrefUtil.getTimerOffset(this)
                stopwatch_chronometer.base = SystemClock.elapsedRealtime() - pauseOffset
                stopwatch_chronometer.start()
                start_pause_button.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.ic_pause
                    )
                )
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        val intent = Intent(this, RecipeDetailsActivity::class.java)
        val bundle = Bundle()
        bundle.putString("RecipeKey", recipe.key)
        intent.putExtras(bundle)
        startActivity(intent)
        finish()
    }
}

