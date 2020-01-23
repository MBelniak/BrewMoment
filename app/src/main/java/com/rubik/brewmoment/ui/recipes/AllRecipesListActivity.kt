package com.rubik.brewmoment.ui.recipes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rubik.brewmoment.R
import com.rubik.brewmoment.model.data.CommonRecipesData
import com.rubik.brewmoment.model.data.RecipesDAO
import com.rubik.brewmoment.view_model.CommonRecipesViewModel
import com.rubik.brewmoment.view_model.FilteredRecipesViewModel
import com.rubik.brewmoment.view_model.Filtering
import kotlinx.android.synthetic.main.activity_all_recipes_list.*

class AllRecipesListActivity : AppCompatActivity() {
    private lateinit var filtering: Filtering
    private lateinit var fragment: FilteredRecipesFragment
    private var actionBar: ActionBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_recipes_list)

        actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        fragment = filtered_recipes_fragment as FilteredRecipesFragment
    }

    private fun setTitle() {
        actionBar?.title = when (filtering) {
            Filtering.All -> resources.getString(R.string.recipes_item_title)
            Filtering.Aeropress -> resources.getString(R.string.menu_aeropress_recipes)
            Filtering.Drip -> resources.getString(R.string.menu_drip_recipes)
            Filtering.FrenchPress -> resources.getString(R.string.menu_french_press_recipes)
        }
    }

    override fun onStart() {
        initFilter(intent)
        setTitle()
        fragment.filtering = filtering
        super.onStart()
    }

    private fun initFilter(intent: Intent) {
        val index: Int? = intent.extras?.getInt("Filtering")
        filtering = if (index != null)
            Filtering.values()[index]
        else
            Filtering.values()[0]
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }
}
