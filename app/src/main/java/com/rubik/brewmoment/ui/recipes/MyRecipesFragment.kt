package com.rubik.brewmoment.ui.recipes

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
import com.rubik.brewmoment.ui.recipes.view_models.MyRecipesViewModel

class MyRecipesFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var rootView: View
    private val myRecipesViewModel: MyRecipesViewModel =
        ViewModelProviders.of(this).get(MyRecipesViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_recipes_tab, container, false)

        myRecipesViewModel.recipes.observe(this, Observer {
                    (recyclerView.adapter as RecipesRecycleViewAdapter).notifyDataSetChanged()})

        linearLayoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)

        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = RecipesRecycleViewAdapter(myRecipesViewModel.recipes.value!!)
        recyclerView.setHasFixedSize(true)

        return super.onCreateView(inflater, container, savedInstanceState)
    }


}