package com.rubik.brewmoment.ui.recipes

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.rubik.brewmoment.MainActivity
import com.rubik.brewmoment.R
import com.rubik.brewmoment.model.GrindLevelEnum
import kotlinx.android.synthetic.main.create_recipe_form1.*
import kotlinx.android.synthetic.main.create_recipe_form2.*
import kotlinx.android.synthetic.main.recipe_step_entry.view.*

class CreateRecipeActivity : AppCompatActivity() {

    private var actionBar: ActionBar? = null
    private lateinit var viewFlipper: ViewFlipper
    private lateinit var stepsLinearLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_recipe)
        actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        viewFlipper = findViewById(R.id.viewFlipper)
        next_button.setOnClickListener{
            viewFlipper.showNext()
        }
        add_step_button.setOnClickListener{addRecipeStep()}
        stepsLinearLayout = (findViewById<ScrollView>(R.id.form2)).findViewById(R.id.steps_linear_layout)
        back_button.setOnClickListener{
            viewFlipper.showPrevious()
        }
        val grindSpinnerAdapter = ArrayAdapter(this, R.layout.spinner_item, GrindLevelEnum.stringArray())
        grind_level_spinner.adapter = grindSpinnerAdapter
    }

    private fun addRecipeStep() {
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView = inflater.inflate(R.layout.recipe_step_entry, null)
        val spinnerArrayAdapter = ArrayAdapter(this, R.layout.spinner_item, resources.getStringArray(R.array.steps))
        rowView.spinner.adapter = spinnerArrayAdapter

        rowView.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener
        {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> {
                        val stepView = inflater.inflate(R.layout.pour_over_step, null)
                        val linLayoutForStep = rowView.findViewById<LinearLayout>(R.id.lin_layout_for_step)
                        if (linLayoutForStep.childCount > 0) linLayoutForStep.removeViewAt(linLayoutForStep.childCount - 1)
                        linLayoutForStep.addView(stepView, linLayoutForStep.childCount - 1)
                    }
                    1 -> {
                        val stepView = inflater.inflate(R.layout.stir_step, null)
                        val linLayoutForStep = rowView.findViewById<LinearLayout>(R.id.lin_layout_for_step)
                        if (linLayoutForStep.childCount > 0) linLayoutForStep.removeViewAt(linLayoutForStep.childCount - 1)
                        linLayoutForStep.addView(stepView, linLayoutForStep.childCount - 1)
                    }
                    2 -> {
                        val stepView = inflater.inflate(R.layout.wait_step, null)
                        val linLayoutForStep = rowView.findViewById<LinearLayout>(R.id.lin_layout_for_step)
                        if (linLayoutForStep.childCount > 0) linLayoutForStep.removeViewAt(linLayoutForStep.childCount - 1)
                        linLayoutForStep.addView(stepView, linLayoutForStep.childCount - 1)
                    }
                    3 -> {
                        val stepView = inflater.inflate(R.layout.press_aeropress_step, null)
                        val linLayoutForStep = rowView.findViewById<LinearLayout>(R.id.lin_layout_for_step)
                        if (linLayoutForStep.childCount > 0) linLayoutForStep.removeViewAt(linLayoutForStep.childCount - 1)
                        linLayoutForStep.addView(stepView, linLayoutForStep.childCount - 1)
                    }
                    4 -> {
                        val stepView = inflater.inflate(R.layout.custom_step, null)
                        val linLayoutForStep = rowView.findViewById<LinearLayout>(R.id.lin_layout_for_step)
                        if (linLayoutForStep.childCount > 0) linLayoutForStep.removeViewAt(linLayoutForStep.childCount - 1)
                        linLayoutForStep.addView(stepView, linLayoutForStep.childCount - 1)
                    }
                }
            }
        }
        stepsLinearLayout.addView(rowView, stepsLinearLayout.childCount - 1)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        AlertDialog.Builder(this)
            .setTitle("Warning")
            .setMessage("Are you sure you want to abort the recipe creation? Progress will not be saved.")

            // Specifying a listener allows you to take an action before dismissing the dialog.
            // The dialog is automatically dismissed when a dialog button is clicked.
            .setPositiveButton(R.string.yes) { _, _ ->
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

            // A null listener allows the button to dismiss the dialog and take no further action.
            .setNegativeButton(R.string.no, null)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
        return true
    }
}
