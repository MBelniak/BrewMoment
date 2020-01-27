package com.rubik.brewmoment.ui.results

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rubik.brewmoment.R
import com.rubik.brewmoment.model.data.*
import com.rubik.brewmoment.ui.recipes.RecipeDetailsActivity
import com.rubik.brewmoment.util.LoginUtil
import com.rubik.brewmoment.view_model.*
import kotlinx.android.synthetic.main.activity_result_details.*
import kotlinx.android.synthetic.main.recipe_item.*

class BrewResultDetailsActivity : AppCompatActivity() {

    private var actionBar: ActionBar? = null
    private lateinit var brewResult: BrewResult
    private lateinit var recipe: Recipe
    private lateinit var brewResultsViewModel: BrewResultsViewModel
    private lateinit var recipesViewModel: RecipesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_details)
        actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        brewResultsViewModel = ViewModelProviders.of(this).get(BrewResultsViewModel::class.java)
        recipesViewModel = ViewModelProviders.of(this).get(RecipesViewModel::class.java)

        recipe_used.text = resources.getString(R.string.recipe_used, "fetching data...")
    }

    override fun onStart() {
        super.onStart()
        initBrewResult(intent)

    }

    private fun initBrewResult(intent: Intent) {
        val key = intent.extras?.getString("ResultKey")
        if (key != null) {
            val res = brewResultsViewModel.getResult(key)
            if (res != null) {
                brewResult = res
                if (brewResult.isRecipeDefault) {
                    recipe = CommonRecipesData.getByKey(brewResult.recipeKey)
                    recipe_used.text = resources.getString(R.string.recipe_used, recipe.title)
                    recipe_used.setOnClickListener {
                        showCommonRecipeDetails()
                    }
                }
                else
                    observeRecipes(brewResult.recipeKey)
                updateView()
            } else {
                Toast.makeText(
                    this,
                    "Result has been deleted in the meantime.",
                    Toast.LENGTH_LONG
                ).show()
                brewResult = BrewResultsDAO.getDefaultResult()
                recipe = CommonRecipesData.getDefaultRecipe()
                updateView()
            }
        }
    }

    private fun observeRecipes(key: String) {
        recipesViewModel.recipes.observe(this, Observer {
            val rec = recipesViewModel.getRecipe(key)
            if (rec != null)
            {
                if (rec.isShared) {
                    recipe = rec
                    recipe_used.text = resources.getString(R.string.recipe_used, recipe.title)
                    recipe_used.setOnClickListener {
                        showRecipeDetails()
                    }
                    updateView()
                }
                else
                {
                    recipe_used.text = resources.getString(R.string.recipe_used, "private recipe")
                    recipe_used.setOnClickListener {}
                    updateView()
                }

            }
            else
            {
                recipe_used.text = resources.getString(R.string.recipe_used, "cannot find recipe")
                recipe_used.setOnClickListener {}
                updateView()
            }
        })
    }

    private fun updateView()
    {
        result_coffee_used.text = resources.getString(R.string.result_coffee_used, brewResult.coffeeBlend)
        result_total_brew_time.text = resources.getString(R.string.result_total_brew_time,
            brewResult.brewTimeMinutes.toString(), getBrewSeconds(brewResult.brewTimeSeconds))
        result_notes.text = resources.getString(R.string.result_notes, brewResult.notes)
        result_author.text = resources.getString(R.string.result_author, brewResult.author)
    }

    private fun getBrewSeconds(brewTimeSeconds: Int): String {
        return if (brewTimeSeconds < 10)
            "0$brewTimeSeconds"
        else brewTimeSeconds.toString()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }

    private fun showCommonRecipeDetails() {
        val intent = Intent(this, RecipeDetailsActivity::class.java)
        val bundle = Bundle()
        bundle.putString("RecipeKey", recipe.key)
        bundle.putBoolean("IsDefault", true)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    private fun showRecipeDetails() {
        val intent = Intent(this, RecipeDetailsActivity::class.java)
        val bundle = Bundle()
        bundle.putString("RecipeKey", recipe.key)
        bundle.putBoolean("IsDefault", false)
        intent.putExtras(bundle)
        startActivity(intent)
    }
}
