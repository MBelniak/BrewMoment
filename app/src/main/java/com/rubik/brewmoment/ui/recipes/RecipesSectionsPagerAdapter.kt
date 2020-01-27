package com.rubik.brewmoment.ui.recipes

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.rubik.brewmoment.R
import com.rubik.brewmoment.model.Filtering

class RecipesSectionsPagerAdapter(private val context: Context, fm: FragmentManager,
                                  private val filt: Filtering = Filtering.ALL)
    : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
{
    private val TAB_TITLES = arrayOf(
        R.string.common_recipes,
        R.string.my_recipes,
        R.string.users_recipes
    )
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return CommonRecipesFragment(filt)
            1 -> return MyRecipesFragment(filt)
            2 -> return UsersRecipesFragment(filt)
        }
        return CommonRecipesFragment(filt)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return 3
    }

}
