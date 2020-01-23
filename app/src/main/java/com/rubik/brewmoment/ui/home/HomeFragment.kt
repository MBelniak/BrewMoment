package com.rubik.brewmoment.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.rubik.brewmoment.R
import com.rubik.brewmoment.ui.recipes.AllRecipesListActivity
import com.rubik.brewmoment.view_model.Filtering
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {
    private lateinit var rootView: View
    private lateinit var aeropressLinLayout: LinearLayout
    private lateinit var dripLinLayout: LinearLayout
    private lateinit var frenchpressLinLayout: LinearLayout
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_home, container, false)

        aeropressLinLayout = rootView.eq_type_aeropress
        dripLinLayout = rootView.eq_type_drip
        frenchpressLinLayout = rootView.eq_type_french
        aeropressLinLayout.setOnClickListener{startRecipesListActivity(Filtering.Aeropress)}
        dripLinLayout.setOnClickListener{startRecipesListActivity(Filtering.Drip)}
        frenchpressLinLayout.setOnClickListener{startRecipesListActivity(Filtering.FrenchPress)}

        return rootView
    }

    private fun startRecipesListActivity(filter: Filtering)  {
        val intent = Intent(activity, AllRecipesListActivity::class.java)
        val bundle = Bundle()
        bundle.putInt("Filtering", filter.ordinal)
        intent.putExtras(bundle)
        startActivity(intent)
    }


}