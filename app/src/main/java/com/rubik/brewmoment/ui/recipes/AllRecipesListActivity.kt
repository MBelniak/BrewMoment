package com.rubik.brewmoment.ui.recipes

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.rubik.brewmoment.R
import com.rubik.brewmoment.model.Filtering

class AllRecipesListActivity : AppCompatActivity() {
    private lateinit var filtering: Filtering
    private var actionBar: ActionBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_recipes_list)
        initFilter(intent)
        actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val sectionsPagerAdapter = RecipesSectionsPagerAdapter(this, supportFragmentManager, filtering)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.all_recipes_tab_layout)
        tabs.setupWithViewPager(viewPager)
    }

    private fun setTitle() {
        actionBar?.title = when (filtering) {
            Filtering.ALL -> resources.getString(R.string.recipes_item_title)
            Filtering.AEROPRESS -> resources.getString(R.string.menu_aeropress_recipes)
            Filtering.DRIP -> resources.getString(R.string.menu_drip_recipes)
            Filtering.CHEMEX -> resources.getString(R.string.menu_chemex_recipes)
            Filtering.FRENCH_PRESS -> resources.getString(R.string.menu_french_press_recipes)
        }
    }

    override fun onStart() {
        setTitle()
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
