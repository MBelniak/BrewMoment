package com.rubik.brewmoment.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.rubik.brewmoment.R
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var aeropressLinLayout: LinearLayout
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
//        homeViewModel.text.observe(this, Observer {
//            textView.text = it
//        })
        aeropressLinLayout = root.eq_type_aeropress
        aeropressLinLayout.setOnClickListener{startAeropressRecipeSearch()}
        return root
    }

    private fun startAeropressRecipeSearch()  {

    }
}