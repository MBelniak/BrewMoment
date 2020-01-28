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
import androidx.core.view.children
import com.rubik.brewmoment.MainActivity
import com.rubik.brewmoment.R
import com.rubik.brewmoment.model.EqTypeEnum
import com.rubik.brewmoment.model.GrindLevelEnum
import com.rubik.brewmoment.model.data.Recipe
import com.rubik.brewmoment.model.data.RecipesDAO
import com.rubik.brewmoment.model.step.*
import com.rubik.brewmoment.util.LoginUtil
import kotlinx.android.synthetic.main.activity_create_recipe.*
import kotlinx.android.synthetic.main.create_recipe_form1.*
import kotlinx.android.synthetic.main.create_recipe_form2.*
import kotlinx.android.synthetic.main.recipe_step_entry.view.*

class CreateRecipeActivity : AppCompatActivity() {

    private var actionBar: ActionBar? = null
    private lateinit var stepsLinearLayout: LinearLayout
    private var isAvailable: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_recipe)
        actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        next_button.setOnClickListener{
            if (validateFields()) {
                view_flipper.setInAnimation(this, R.anim.right_in)
                view_flipper.setOutAnimation(this, R.anim.left_out)
                view_flipper.showNext()
            }
        }
        add_step_button.setOnClickListener{addRecipeStep()}
        stepsLinearLayout = (findViewById<ScrollView>(R.id.form2)).findViewById(R.id.steps_linear_layout)
        back_button.setOnClickListener{
            view_flipper.setInAnimation(this, R.anim.left_in)
            view_flipper.setOutAnimation(this, R.anim.right_out)
            view_flipper.showPrevious()
        }
        save_recipe_button.setOnClickListener{
            if (validateFields())
                saveRecipe(false)
        }
        save_and_share_recipe_button.setOnClickListener{
            if (validateFields())
                saveRecipe(true)
        }

        val grindSpinnerAdapter = ArrayAdapter(this, R.layout.spinner_item, GrindLevelEnum.stringArray())
        grind_level_spinner.adapter = grindSpinnerAdapter

        val eqSpinnerAdapter = ArrayAdapter(this, R.layout.spinner_item, EqTypeEnum.stringArray())
        eq_spinner.adapter = eqSpinnerAdapter
    }

    override fun onStart() {
        super.onStart()
        if (!LoginUtil.isUserLoggedIn())
        {
            not_logged_in_text_view.visibility = View.VISIBLE
            view_flipper.visibility = View.GONE
            isAvailable = false
        }
        else
        {
            not_logged_in_text_view.visibility = View.GONE
            view_flipper.visibility = View.VISIBLE
            isAvailable = true
        }
    }

    private fun validateFields(): Boolean {
        var result = true
        if (title_edit_text.text.isBlank()) {
            title_edit_text.error = "This field is mandatory"
            result = false
        }
        if (brew_time_minute_edit_text.text.isBlank()) {
            brew_time_minute_edit_text.error = "This field is mandatory"
            result = false
        }
        if (brew_time_seconds_edit_text.text.isBlank()) {
            brew_time_seconds_edit_text.error = "This field is mandatory"
            result = false
        }
        else if (brew_time_seconds_edit_text.text.toString().toInt() > 59) {
            brew_time_seconds_edit_text.error = "Incorrect value"
            result = false
        }
        if (water_temperature_edit_text.text.isBlank()) {
            water_temperature_edit_text.error = "This field is mandatory"
            result = false
        }
        else if (water_temperature_edit_text.text.toString().toFloat() > 100f)
        {
            water_temperature_edit_text.error = "Incorrect value"
            result = false
        }
        if (doze_edit_text.text.isBlank()) {
            doze_edit_text.error = "This field is mandatory"
            result = false
        }
        if (total_water_edit_text.text.isBlank()) {
            total_water_edit_text.error = "This field is mandatory"
            result = false
        }
        for (child in stepsLinearLayout.children)
        {
            when (child.spinner.selectedItemPosition) {
                0 -> {  //Pour over
                    if (child.findViewById<EditText>(R.id.pour_water_amount).text.isBlank())
                    {
                        child.findViewById<EditText>(R.id.pour_water_amount).error = "This field is mandatory"
                        result = false
                    }
                    if (child.findViewById<EditText>(R.id.pour_water_time).text.isBlank())
                    {
                        child.findViewById<EditText>(R.id.pour_water_time).error = "This field is mandatory"
                        result = false
                    }
                }
                1 -> {} //Stir
                2 -> {  // Wait
                    if (child.findViewById<EditText>(R.id.wait_minutes).text.isBlank())
                    {
                        child.findViewById<EditText>(R.id.wait_minutes).error = "This field is mandatory"
                        result = false
                    }
                    if (child.findViewById<EditText>(R.id.wait_seconds).text.isBlank())
                    {
                        child.findViewById<EditText>(R.id.wait_seconds).error = "This field is mandatory"
                        result = false
                    }
                    else if (child.findViewById<EditText>(R.id.wait_seconds).text.toString().toInt() > 59)
                    {
                        child.findViewById<EditText>(R.id.wait_seconds).error = "Incorrect value"
                        result = false
                    }
                }
                3 -> { // Press
                    if (child.findViewById<EditText>(R.id.press_seconds).text.isBlank())
                    {
                        child.findViewById<EditText>(R.id.press_seconds).error = "This field is mandatory"
                        result = false
                    }
                }
                4 -> {
                    if (child.findViewById<EditText>(R.id.custom_step_text).text.isBlank())
                    {
                        child.findViewById<EditText>(R.id.custom_step_text).error = "This field is mandatory"
                        result = false
                    }
                }
            }
        }
        return result
    }

    private fun saveRecipe(isShared: Boolean) {
        val user = LoginUtil.getCurrentUser()
        if (user != null)
        {
            val recipe = Recipe(
                title_edit_text.text.toString(),
                user.displayName.toString(),
                user.email.toString(),
                description_edit_text.text.toString(),
                EqTypeEnum.values()[eq_spinner.selectedItemPosition],
                brew_time_minute_edit_text.text.toString().toInt(),
                brew_time_seconds_edit_text.text.toString().toInt(),
                GrindLevelEnum.values()[grind_level_spinner.selectedItemPosition],
                water_temperature_edit_text.text.toString().toFloat(),
                doze_edit_text.text.toString().toFloat(),
                totalWater = total_water_edit_text.text.toString().toFloat(),
                steps = getCreatedSteps(), isShared = isShared, isDefault = false
            )
            RecipesDAO.insert(recipe)
            Toast.makeText(this, "Recipe will be saved",
                Toast.LENGTH_LONG).show()
        }
        else
        {
            Toast.makeText(this, "You have been logged out. Recipe not saved.",
                Toast.LENGTH_LONG).show()
        }
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun getCreatedSteps(): List<Step> {
        val result: MutableList<Step> = mutableListOf()
        for (child in stepsLinearLayout.children)
        {
            val spinner = child.spinner
            when (spinner.selectedItemPosition)
            {
                0 -> {
                    val step = PourOverStep(child.findViewById<EditText>(R.id.author_tips).text.toString(),
                        child.findViewById<EditText>(R.id.pour_water_amount).text.toString().toInt(),
                        child.findViewById<EditText>(R.id.pour_water_time).text.toString().toInt())
                    result.add(step)}
                1 -> {
                    val step = StirStep(child.findViewById<EditText>(R.id.author_tips).text.toString())
                    result.add(step)}
                2 -> {
                    val step = WaitStep(child.findViewById<EditText>(R.id.author_tips).text.toString(),
                        child.findViewById<EditText>(R.id.wait_minutes).text.toString().toInt(),
                        child.findViewById<EditText>(R.id.wait_seconds).text.toString().toInt())
                    result.add(step)}
                3 -> {
                    val step = PressAeroStep(child.findViewById<EditText>(R.id.author_tips).text.toString(),
                            child.findViewById<EditText>(R.id.press_seconds).text.toString().toInt())
                    result.add(step)}
                4 -> {
                    val step = CustomStep(child.findViewById<EditText>(R.id.custom_step_text).text.toString())
                    result.add(step)}
            }
        }
        return result
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
        stepsLinearLayout.addView(rowView, stepsLinearLayout.childCount)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (isAvailable) {
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
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
        return true
    }
}
