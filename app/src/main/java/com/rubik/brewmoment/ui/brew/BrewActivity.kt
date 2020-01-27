package com.rubik.brewmoment.ui.brew

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.rubik.brewmoment.R
import com.rubik.brewmoment.model.data.CommonRecipesData
import com.rubik.brewmoment.model.data.Recipe
import com.rubik.brewmoment.model.data.RecipesDAO
import com.rubik.brewmoment.model.step.BoilWaterStep
import com.rubik.brewmoment.model.step.GrindStep
import com.rubik.brewmoment.model.step.Step
import com.rubik.brewmoment.ui.recipes.RecipeDetailsActivity
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
    private var steps = mutableListOf<Step>()
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
        val grindBeansStep = GrindStep("", recipe.grindLevel, recipe.dose)
        val boilWaterStep = BoilWaterStep("Also, " + grindBeansStep.description, recipe.temperature)
        steps.add(boilWaterStep)
        steps.addAll(recipe.steps)
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        for (step in steps)
        {
            val instructionFragment = inflater.inflate(R.layout.fragment_instruction, null)
            instructionFragment.findViewById<TextView>(R.id.brew_instruction).text = step.description
            instructionFragment.findViewById<TextView>(R.id.brew_authors_tip).text = step.authorsTips
            view_flipper.addView(instructionFragment)
        }
        setListeners()
    }

    private fun setListeners() {
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
            when (currentStep < steps.size - 1) {
                true -> {
                    view_flipper.setInAnimation(this, R.anim.right_in)
                    view_flipper.setOutAnimation(this, R.anim.left_out)
                    currentStep++
                    view_flipper.showNext()
                    updateButtons()
                }
                false -> finishBrewing()
            }
        }

        back.setOnClickListener{
            if (back.isVisible) {
                view_flipper.setInAnimation(this, R.anim.left_in)
                view_flipper.setOutAnimation(this, R.anim.right_out)
                currentStep--
                view_flipper.showPrevious()
                updateButtons()
            }
        }
    }

    private fun initRecipe(intent: Intent) {
        val key: String? = intent.extras?.getString("RecipeKey")
        val isDefault: Boolean? = intent.extras?.getBoolean("IsDefault")
        if (key != null)
            if (isDefault == null) {
                recipe = CommonRecipesData.getDefaultRecipe()
                Toast.makeText(this, "Cannot find recipe, using default one", Toast.LENGTH_LONG).show()
            }
            else if (isDefault)
            {
                recipe = CommonRecipesData.getByKey(key)
            }
            else
            {
                val rec = RecipesDAO.getByKey(key)
                if (rec == null) {
                    recipe = CommonRecipesData.getDefaultRecipe()
                    Toast.makeText(this, "Recipe has been deleted in the meantime.", Toast.LENGTH_LONG).show()
                }
                else {
                    recipe = rec
                }
            }
        else {
            Toast.makeText(this, "Cannot find recipe, using default one", Toast.LENGTH_LONG).show()
            recipe = CommonRecipesData.getDefaultRecipe()
        }
    }


    private fun finishBrewing() {
        val intent = Intent(this, BrewResultActivity::class.java)
        val bundle = Bundle()
        if (stopwatchState == State.Running)
            pauseStopwatch()
        val seconds = (pauseOffset / 1000).toInt()
        bundle.putInt("Minutes", seconds/60)
        bundle.putInt("Seconds", seconds%60)
        bundle.putString("RecipeKey", recipe.key)
        bundle.putBoolean("IsDefault", recipe.isDefault)
        bundle.putInt("EqType", recipe.equipment.ordinal)
        intent.putExtras(bundle)
        startActivity(intent)
        pauseStopwatch()
        finish()
    }

    private fun updateButtons() {
        back.text = getString(R.string.back)
        back.visibility = if (currentStep == 0) View.INVISIBLE else View.VISIBLE
        next.text = if (currentStep == steps.size - 1) getString(R.string.finish) else getString(R.string.next)
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
        stopwatch_chronometer.stop()
        stopwatch_chronometer.base = SystemClock.elapsedRealtime()
        start_pause_button.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_play_arrow))
        pauseOffset = 0
    }

    override fun onResume() {
        super.onResume()
        stopwatchResume()
        updateButtons()
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
        bundle.putBoolean("IsDefault", recipe.isDefault)
        intent.putExtras(bundle)
        startActivity(intent)
        finish()
    }
}

