package com.rubik.brewmoment.ui.recipes.chosen_recipe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.rubik.brewmoment.R
import com.rubik.brewmoment.model.data.CommonRecipesData
import com.rubik.brewmoment.model.data.Recipe
import com.rubik.brewmoment.model.data.RecipesDAO
import com.rubik.brewmoment.ui.brew.BrewActivity
import kotlinx.android.synthetic.main.activity_recipe_details.*

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
        chosen_recipe_eq.text = applicationContext.getString(R.string.chosen_recipe_eq, recipe.equipment.EqName)
        chosen_recipe_author.text = applicationContext.getString(R.string.chosen_recipe_author, recipe.author)
        chosen_recipe_grind_level.text = applicationContext.getString(R.string.chosen_recipe_grind_level, recipe.grindLevel.grindLevel)
        chosen_recipe_brew_temperature.text = applicationContext.getString(R.string.chosen_recipe_brew_temperature, recipe.getTemperature())
        chosen_recipe_brew_time.text = applicationContext.getString(R.string.chosen_recipe_brew_time, recipe.brewTimeToString())
        chosen_recipe_steps.text = applicationContext.getString(R.string.chosen_recipe_steps, recipe.getStepsAsString())

        chosen_recipe_brew_button.setOnClickListener {
            val intent = Intent(this, BrewActivity::class.java)
            val bundle = Bundle()
            bundle.putString("RecipeKey", recipe.key)
            bundle.putBoolean("IsDefault", true)
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
            recipe = if (isDefault != null)
                CommonRecipesData.getByKey(key)
            else
                RecipesDAO.getByKey(key)
        else
            CommonRecipesData.getDefaultRecipe()
    }
}
