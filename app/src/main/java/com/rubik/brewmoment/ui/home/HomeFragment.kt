package com.rubik.brewmoment.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rubik.brewmoment.R
import com.rubik.brewmoment.ui.recipes.AllRecipesListActivity
import com.rubik.brewmoment.ui.recipes.CreateRecipeActivity
import com.rubik.brewmoment.view_model.Filtering
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    private lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_home, container, false)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        aeropress_linear_layout.setOnClickListener{startRecipesListActivity(Filtering.Aeropress)}
        drip_linear_layout.setOnClickListener{startRecipesListActivity(Filtering.Drip)}
        chemex_linear_layout.setOnClickListener{startRecipesListActivity(Filtering.Chemex)}
        french_linear_layout.setOnClickListener{startRecipesListActivity(Filtering.FrenchPress)}
        create_recipe_lin_layout.setOnClickListener{startCreateRecipeActivity()}
    }

    private fun startCreateRecipeActivity() {
        val intent = Intent(activity, CreateRecipeActivity::class.java)
        startActivity(intent)
    }

    private fun startRecipesListActivity(filter: Filtering)  {
        val intent = Intent(activity, AllRecipesListActivity::class.java)
        val bundle = Bundle()
        bundle.putInt("Filtering", filter.ordinal)
        intent.putExtras(bundle)
        startActivity(intent)
    }
}
