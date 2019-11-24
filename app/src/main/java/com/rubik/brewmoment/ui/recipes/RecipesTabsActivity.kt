package com.rubik.brewmoment.ui.recipes

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rubik.brewmoment.R
import com.rubik.brewmoment.model.EqTypeEnum
import com.rubik.brewmoment.ui.recipes.ui.main.SectionsPagerAdapter

class RecipesTabsActivity : AppCompatActivity() {
    private val FIXED_EQUIPMENT = "Fixed equipment"
    private lateinit var recipesViewModel: RecipesViewModel
    private var fixedEquipmentType: EqTypeEnum? = null
    private lateinit var recipeBrowseTitle: TextView
    private lateinit var myRecipesRecycleView : RecyclerView
    private lateinit var builtInRecyclerView : RecyclerView
    private lateinit var usersRecipeRecyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fixedEquipmentType = savedInstanceState?.getString(FIXED_EQUIPMENT)?.let { EqTypeEnum.valueOf(it)}
        recipeBrowseTitle = findViewById(R.id.recipes_title)

        myRecipesRecycleView.layoutManager = LinearLayoutManager(this)
        myRecipesRecycleView.setHasFixedSize(true)
        builtInRecyclerView.layoutManager = LinearLayoutManager(this)
        builtInRecyclerView.setHasFixedSize(true)
        usersRecipeRecyclerView.layoutManager = LinearLayoutManager(this)
        usersRecipeRecyclerView.setHasFixedSize(true)


        setContentView(R.layout.activity_recipes_tabs)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.recipes_tab_layout)
        tabs.setupWithViewPager(viewPager)

    }


}