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
import com.rubik.brewmoment.view_model.MyRecipesViewModel
import com.rubik.brewmoment.view_model.MyRecipesViewModelFactory

class MyRecipesFragment(private val filt: Filtering = Filtering.ALL) : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var rootView: View
    private lateinit var myRecipesViewModel: MyRecipesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_recipes_list, container, false)
        linearLayoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        myRecipesViewModel = ViewModelProviders.of(this,
            MyRecipesViewModelFactory(this.activity!!.application,
                LoginUtil.getCurrentUserEmail().toString()
            )).get(MyRecipesViewModel::class.java)

        recyclerView = rootView.findViewById(R.id.recipe_recycle_view)
        recyclerView.adapter = RecipesRecyclerViewAdapter(myRecipesViewModel.getFilteredRecipes(filt),
            activity!!.applicationContext)
        (recyclerView.adapter as RecipesRecyclerViewAdapter).setOnItemClickListener(object : OnItemClickListener {
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

        myRecipesViewModel.recipes.observe(this, Observer {
            (recyclerView.adapter as RecipesRecyclerViewAdapter).recipesDataset = myRecipesViewModel.getFilteredRecipes(filt)
            (recyclerView.adapter as RecipesRecyclerViewAdapter).notifyDataSetChanged()
            updateUI()
        })
        return rootView
    }

    override fun onStart() {
        super.onStart()
        updateUI()
    }

    private fun updateUI() {
        if (!LoginUtil.isUserLoggedIn())
        {
            recyclerView.visibility = View.GONE
            rootView.findViewById<TextView>(R.id.no_data_available).visibility = View.GONE
            rootView.findViewById<TextView>(R.id.not_logged_in).visibility = View.VISIBLE
            rootView.findViewById<TextView>(R.id.choose_a_recipe_title).visibility = View.GONE
        }
        else
        {
            val recipes = myRecipesViewModel.getFilteredRecipes(filt)
            if (recipes.isEmpty()) {
                recyclerView.visibility = View.GONE
                rootView.findViewById<TextView>(R.id.no_data_available).visibility = View.VISIBLE
                rootView.findViewById<TextView>(R.id.not_logged_in).visibility = View.GONE
                rootView.findViewById<TextView>(R.id.choose_a_recipe_title).visibility = View.GONE
            }
            else
            {
                recyclerView.visibility = View.VISIBLE
                rootView.findViewById<TextView>(R.id.no_data_available).visibility = View.GONE
                rootView.findViewById<TextView>(R.id.not_logged_in).visibility = View.GONE
                rootView.findViewById<TextView>(R.id.choose_a_recipe_title).visibility = View.VISIBLE

            }
        }
    }
}