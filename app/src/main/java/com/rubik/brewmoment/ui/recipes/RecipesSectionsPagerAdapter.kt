package com.rubik.brewmoment.ui.recipes

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.rubik.brewmoment.R

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class RecipesSectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {
    private val TAB_TITLES = arrayOf(
        R.string.my_recipes,
        R.string.built_in_recipes,
        R.string.users_recipes
    )
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return MyRecipesFragment()
            1 -> return BuiltInRecipesFragment()
            2 -> return OtherRecipesFragment()
        }
        return MyRecipesFragment()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return 3
    }
}