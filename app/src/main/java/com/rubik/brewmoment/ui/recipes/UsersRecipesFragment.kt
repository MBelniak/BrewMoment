package com.rubik.brewmoment.ui.recipes

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rubik.brewmoment.R
import com.rubik.brewmoment.model.Filtering
import com.rubik.brewmoment.model.data.Recipe
import com.rubik.brewmoment.util.LoginUtil
import com.rubik.brewmoment.view_model.RecipesViewModel

class UsersRecipesFragment(private val filt: Filtering = Filtering.ALL) : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var rootView: View
    private lateinit var recipesViewModel: RecipesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_recipes_list, container, false)
        recipesViewModel = ViewModelProviders.of(this).get(RecipesViewModel::class.java)
        linearLayoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        recyclerView = rootView.findViewById(R.id.recipe_recycle_view)
        recyclerView.adapter = RecipesRecyclerViewAdapter(recipesViewModel.getUsersFilteredRecipes(filt),
            activity!!.applicationContext)
        (recyclerView.adapter as RecipesRecyclerViewAdapter).setOnItemClickListener(
            object :
                OnItemClickListener {
                override fun onItemClick(recipe: Recipe) {
                    val intent = Intent(activity, RecipeDetailsActivity::class.java)
                    val bundle = Bundle()
                    bundle.putString("RecipeKey", recipe.key)
                    bundle.putBoolean("IsDefault", recipe.isDefault)
                    intent.putExtras(bundle)
                    startActivity(intent)
                }
            })

        recyclerView.layoutManager = linearLayoutManager
        recyclerView.setHasFixedSize(true)

        initUI()

        recipesViewModel.recipes.observe(this, Observer {
            (recyclerView.adapter as RecipesRecyclerViewAdapter).recipesDataset = recipesViewModel.getUsersFilteredRecipes(filt)
            (recyclerView.adapter as RecipesRecyclerViewAdapter).notifyDataSetChanged()
            updateUI()
        })

        return rootView
    }

    private fun initUI() {
        if (!LoginUtil.isUserLoggedIn())
        {
            recyclerView.visibility = View.GONE
            rootView.findViewById<TextView>(R.id.no_data_available).visibility = View.GONE
            rootView.findViewById<TextView>(R.id.not_logged_in).visibility = View.VISIBLE
            rootView.findViewById<TextView>(R.id.choose_a_recipe_title).visibility = View.GONE
        }
        else
        {
            recyclerView.visibility = View.GONE
            rootView.findViewById<TextView>(R.id.no_data_available).visibility = View.GONE
            rootView.findViewById<TextView>(R.id.loading_data).visibility = View.VISIBLE
            rootView.findViewById<TextView>(R.id.not_logged_in).visibility = View.GONE
            rootView.findViewById<TextView>(R.id.choose_a_recipe_title).visibility = View.GONE
        }
    }

    private fun updateUI() {
        if (LoginUtil.isUserLoggedIn()) {
            if ((recyclerView.adapter as RecipesRecyclerViewAdapter).recipesDataset.isNotEmpty()) {
                recyclerView.visibility = View.VISIBLE
                rootView.findViewById<TextView>(R.id.no_data_available).visibility = View.GONE
                rootView.findViewById<TextView>(R.id.loading_data).visibility = View.GONE
                rootView.findViewById<TextView>(R.id.not_logged_in).visibility = View.GONE
                rootView.findViewById<TextView>(R.id.choose_a_recipe_title).visibility =
                    View.VISIBLE
            } else {
                recyclerView.visibility = View.VISIBLE
                rootView.findViewById<TextView>(R.id.no_data_available).visibility = View.VISIBLE
                rootView.findViewById<TextView>(R.id.loading_data).visibility = View.GONE
                rootView.findViewById<TextView>(R.id.not_logged_in).visibility = View.GONE
                rootView.findViewById<TextView>(R.id.choose_a_recipe_title).visibility = View.VISIBLE
            }
        }
    }
}