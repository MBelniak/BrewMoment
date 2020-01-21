package com.rubik.brewmoment.ui.recipes.chosen_recipe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import com.rubik.brewmoment.R
import com.rubik.brewmoment.model.data.CommonRecipesData
import com.rubik.brewmoment.model.data.Recipe
import com.rubik.brewmoment.model.data.RecipesDAO

class RecipeDetailsActivity : AppCompatActivity() {

    private lateinit var recipe: Recipe
    private lateinit var recipeTitle: TextView
    private lateinit var recipeEq: TextView
    private lateinit var recipeAuthor: TextView
    private lateinit var recipeGrindLevel: TextView
    private lateinit var recipeTemperature: TextView
    private lateinit var recipeDescription: TextView
    private lateinit var recipeSteps: TextView
    private lateinit var recipeBrewTime: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_details)
        initRecipe(intent)

        val actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        recipeTitle = findViewById(R.id.chosen_recipe_title)
        recipeEq = findViewById(R.id.chosen_recipe_eq)
        recipeAuthor = findViewById(R.id.chosen_recipe_author)
        recipeBrewTime = findViewById(R.id.chosen_recipe_brew_time)
        recipeGrindLevel = findViewById(R.id.chosen_recipe_grind_level)
        recipeTemperature = findViewById(R.id.chosen_recipe_brew_temperature)
        recipeDescription = findViewById(R.id.chosen_recipe_description)
        recipeSteps = findViewById(R.id.chosen_recipe_steps)

        recipeTitle.text = recipe.title
        recipeEq.text = applicationContext.getString(R.string.chosen_recipe_eq, recipe.equipment.EqName)
        recipeAuthor.text = applicationContext.getString(R.string.chosen_recipe_author, recipe.author)
        recipeGrindLevel.text = applicationContext.getString(R.string.chosen_recipe_grind_level, recipe.grindLevel.grindLevel)
        recipeTemperature.text = applicationContext.getString(R.string.chosen_recipe_brew_temperature, recipe.getTemperature())
        recipeBrewTime.text = applicationContext.getString(R.string.chosen_recipe_brew_time, recipe.brewTimeToString())
        recipeSteps.text = applicationContext.getString(R.string.chosen_recipe_steps, recipe.getStepsAsString())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }

    private fun initRecipe(intent: Intent) {
        val id: Int? = intent.extras?.getInt("RecipeId")
        val isDefault: Boolean? = intent.extras?.getBoolean("IsDefault")
        if (id != null)
            recipe = if (isDefault != null)
                CommonRecipesData.getById(id)
            else
                RecipesDAO.getById(id)
        else
            CommonRecipesData.getById(0)
    }
}
