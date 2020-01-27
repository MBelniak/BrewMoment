package com.rubik.brewmoment.ui.recipes

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rubik.brewmoment.R
import com.rubik.brewmoment.model.Filtering
import com.rubik.brewmoment.model.data.Recipe
import com.rubik.brewmoment.view_model.CommonRecipesViewModel

class CommonRecipesFragment(private val filt: Filtering = Filtering.ALL) : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var rootView: View
    private lateinit var commonRecipesViewModel: CommonRecipesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_recipes_list, container, false)
        linearLayoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)

        commonRecipesViewModel = ViewModelProviders.of(this).get(CommonRecipesViewModel::class.java)
        recyclerView = rootView.findViewById(R.id.recipe_recycle_view)

        return rootView
    }

    override fun onStart() {
        super.onStart()

        val recipes = commonRecipesViewModel.getFilteredRecipes(filt)
        if (recipes.isEmpty()) {
            recyclerView.visibility = View.GONE
            rootView.findViewById<TextView>(R.id.no_data_available).visibility = View.VISIBLE
            rootView.findViewById<TextView>(R.id.not_logged_in).visibility = View.GONE
            rootView.findViewById<TextView>(R.id.choose_a_recipe_title).visibility = View.GONE
        }
        else {
            recyclerView.adapter = RecipesRecyclerViewAdapter(
                commonRecipesViewModel.getFilteredRecipes(filt),
                activity!!.applicationContext
            )

            (recyclerView.adapter as RecipesRecyclerViewAdapter).setOnItemClickListener(object :
                OnItemClickListener {
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
        }
    }
}