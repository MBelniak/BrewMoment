package com.rubik.brewmoment.ui.recipes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.rubik.brewmoment.R
import com.rubik.brewmoment.model.data.CommonRecipesData
import com.rubik.brewmoment.model.data.Recipe
import com.rubik.brewmoment.model.data.RecipesDAO
import com.rubik.brewmoment.ui.brew.BrewActivity
import kotlinx.android.synthetic.main.activity_recipe_details.*
import java.text.NumberFormat

class RecipeDetailsActivity : AppCompatActivity() {

    private lateinit var recipe: Recipe

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_details)
        initRecipe(intent)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.title = resources.getString(R.string.recipe_details_title)

        chosen_recipe_title.text = recipe.title
        chosen_recipe_eq.text = resources.getString(R.string.chosen_recipe_eq, recipe.equipment.EqName)
        chosen_recipe_author.text = applicationContext.getString(R.string.chosen_recipe_author, recipe.author)
        chosen_recipe_description.text = recipe.description
        chosen_recipe_grind_level.text = resources.getString(R.string.chosen_recipe_grind_level, recipe.grindLevel.grindLevel)
        chosen_recipe_brew_temperature.text = resources.getString(R.string.chosen_recipe_brew_temperature,
            NumberFormat.getInstance().format(recipe.temperature)+"°C")
        chosen_recipe_brew_time.text = resources.getString(R.string.chosen_recipe_brew_time, recipe.brewTimeToString())
        chosen_recipe_brew_dose.text = resources.getString(R.string.chosen_recipe_brew_dose,
            NumberFormat.getInstance().format(recipe.dose) + "g")
        chosen_recipe_brew_total_water.text = resources.getString(R.string.chosen_recipe_brew_total_water,
            NumberFormat.getInstance().format(recipe.totalWater) + "ml")
        chosen_recipe_steps.text = applicationContext.getString(R.string.chosen_recipe_steps, recipe.getStepsAsString())

        start_brewing_button.setOnClickListener {
            val intent = Intent(this, BrewActivity::class.java)
            val bundle = Bundle()
            bundle.putString("RecipeKey", recipe.key)
            bundle.putBoolean("IsDefault", recipe.isDefault)
            intent.putExtras(bundle)
            startActivity(intent)
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
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
            else {
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
            recipe = CommonRecipesData.getDefaultRecipe()
            Toast.makeText(this, "Cannot find recipe, using default one", Toast.LENGTH_LONG).show()
        }
    }
}
