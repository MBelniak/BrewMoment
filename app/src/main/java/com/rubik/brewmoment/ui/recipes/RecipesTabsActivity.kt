package com.rubik.brewmoment.ui.recipes

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import com.rubik.brewmoment.R
import com.rubik.brewmoment.model.EqTypeEnum

class RecipesTabsActivity : AppCompatActivity() {
    private val FIXED_EQUIPMENT = "Fixed equipment"
    private var fixedEquipmentType: EqTypeEnum? = null
    private lateinit var recipeBrowseTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fixedEquipmentType = savedInstanceState?.getString(FIXED_EQUIPMENT)?.let { EqTypeEnum.valueOf(it)}
        recipeBrowseTitle = findViewById(R.id.recipes_title)
        recipeBrowseTitle.text = "Choose recipe"

        setContentView(R.layout.activity_recipes_tabs)
        val sectionsPagerAdapter =
            RecipesSectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.recipes_tab_layout)
        tabs.setupWithViewPager(viewPager)

    }


}