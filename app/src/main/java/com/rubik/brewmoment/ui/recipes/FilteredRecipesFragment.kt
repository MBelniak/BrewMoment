package com.rubik.brewmoment.ui.recipes

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rubik.brewmoment.R
import com.rubik.brewmoment.model.data.Recipe
import com.rubik.brewmoment.ui.recipes.chosen_recipe.RecipeDetailsActivity
import com.rubik.brewmoment.view_model.FilteredRecipesViewModel
import com.rubik.brewmoment.view_model.Filtering

class FilteredRecipesFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var rootView: View
    private lateinit var allRecipesViewModel: FilteredRecipesViewModel
    var filtering: Filtering = Filtering.All
        set(value) {
            if (value != field)
                allRecipesViewModel.filtering = value
            field = value
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_recipes_list, container, false)
        linearLayoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)

        allRecipesViewModel = ViewModelProviders.of(this).get(FilteredRecipesViewModel::class.java)
        allRecipesViewModel.filtering = filtering
        recyclerView = rootView.findViewById(R.id.recipe_recycle_view)

        return rootView
    }

    override fun onStart() {
        recyclerView.adapter = RecipesRecycleViewAdapter(allRecipesViewModel.recipes, activity!!.applicationContext)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.setHasFixedSize(true)

        (recyclerView.adapter as RecipesRecycleViewAdapter).setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(recipe: Recipe) {
                val intent = Intent(activity, RecipeDetailsActivity::class.java)
                val bundle = Bundle()
                bundle.putString("RecipeKey", recipe.key)
                bundle.putBoolean("IsDefault", true)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        })

        recyclerView.layoutManager = linearLayoutManager
        recyclerView.setHasFixedSize(true)
        super.onStart()
    }
}