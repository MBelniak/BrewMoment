package com.rubik.brewmoment.ui.recipes

import androidx.fragment.app.Fragment
import com.rubik.brewmoment.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager


class RecipesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var rootView: View
    private lateinit var recipesViewModel: RecipesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_recipes_tab, container, false)
        recipesViewModel = ViewModelProviders.of(this).get(RecipesViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
        recyclerView = rootView.findViewById(R.id.recipe_recycle_view)

        linearLayoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)

        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = RecipesRecycleViewAdapter(recipesViewModel.allRecipes)
        recyclerView.setHasFixedSize(true)
        recipesViewModel.allRecipes.observe(this, Observer {
            (recyclerView.adapter as RecipesRecycleViewAdapter).notifyDataSetChanged()
        })

        return super.onCreateView(inflater, container, savedInstanceState)
    }



    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): RecipesFragment {
            return RecipesFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}