package com.rubik.brewmoment.ui.recipes

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rubik.brewmoment.R
import com.rubik.brewmoment.model.data.Recipe
import com.rubik.brewmoment.ui.recipes.chosen_recipe.RecipeDetailsActivity
import com.rubik.brewmoment.view_model.UsersRecipesViewModel



class UsersRecipesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var rootView: View
    private lateinit var usersRecipesViewModel: UsersRecipesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_recipes_list, container, false)
        usersRecipesViewModel = ViewModelProviders.of(this).get(UsersRecipesViewModel::class.java)
        linearLayoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        recyclerView = rootView.findViewById(R.id.recipe_recycle_view)

        recyclerView.adapter = RecipesRecyclerViewAdapter(
            usersRecipesViewModel.recipes.value!!, activity!!.applicationContext)
        usersRecipesViewModel.recipes.observe(this, Observer {
            (recyclerView.adapter as RecipesRecyclerViewAdapter).notifyDataSetChanged()})

        (recyclerView.adapter as RecipesRecyclerViewAdapter).setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(recipe: Recipe) {
                val intent = Intent(activity, RecipeDetailsActivity::class.java)
                val bundle = Bundle()
                bundle.putString("RecipeKeey", recipe.key)
                bundle.putBoolean("IsDefault", true)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        })

        recyclerView.layoutManager = linearLayoutManager
        recyclerView.setHasFixedSize(true)

        return rootView
    }
}