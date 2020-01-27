package com.rubik.brewmoment.ui.results

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
import com.rubik.brewmoment.model.data.BrewResult
import com.rubik.brewmoment.util.LoginUtil
import com.rubik.brewmoment.view_model.BrewResultsViewModel

class MyBrewResultsFragment : Fragment() {

    private lateinit var brewResultsViewModel: BrewResultsViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_results_list, container, false)
        linearLayoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        recyclerView = rootView.findViewById(R.id.results_recycle_view)

        brewResultsViewModel = ViewModelProviders.of(this).get(BrewResultsViewModel::class.java)

        recyclerView.adapter = BrewResultsRecyclerViewAdapter(
            brewResultsViewModel.getMyResults(LoginUtil.getCurrentUserEmail()) , activity!!.applicationContext)

        (recyclerView.adapter as BrewResultsRecyclerViewAdapter).setOnItemClickListener(object :
            OnResultItemClickListener {
            override fun onItemClick(brewResult: BrewResult) {
                val intent = Intent(activity, BrewResultDetailsActivity::class.java)
                val bundle = Bundle()
                bundle.putString("ResultKey", brewResult.key)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        })
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.setHasFixedSize(true)

        initUI()

        brewResultsViewModel.results.observe(this, Observer {
            (recyclerView.adapter as BrewResultsRecyclerViewAdapter).resultsDataset =
                brewResultsViewModel.getMyResults(LoginUtil.getCurrentUserEmail())
            (recyclerView.adapter as BrewResultsRecyclerViewAdapter).showFavourites = true
            (recyclerView.adapter as BrewResultsRecyclerViewAdapter).notifyDataSetChanged()
            updateUI()
        })

        return rootView
    }

    private fun initUI() {
        if (!LoginUtil.isUserLoggedIn())
        {
            recyclerView.visibility = View.GONE
            rootView.findViewById<TextView>(R.id.no_data_available).visibility = View.GONE
            rootView.findViewById<TextView>(R.id.loading_data).visibility = View.GONE
            rootView.findViewById<TextView>(R.id.not_logged_in).visibility = View.VISIBLE
        }
        else
        {
            recyclerView.visibility = View.GONE
            rootView.findViewById<TextView>(R.id.no_data_available).visibility = View.GONE
            rootView.findViewById<TextView>(R.id.loading_data).visibility = View.VISIBLE
            rootView.findViewById<TextView>(R.id.not_logged_in).visibility = View.GONE
        }
    }


    private fun updateUI() {
        if (LoginUtil.isUserLoggedIn())
        {
            if ((recyclerView.adapter as BrewResultsRecyclerViewAdapter).resultsDataset.isNotEmpty())
            {
                recyclerView.visibility = View.VISIBLE
                rootView.findViewById<TextView>(R.id.no_data_available).visibility = View.GONE
                rootView.findViewById<TextView>(R.id.loading_data).visibility = View.GONE
                rootView.findViewById<TextView>(R.id.not_logged_in).visibility = View.GONE
            }
            else {
                recyclerView.visibility = View.GONE
                rootView.findViewById<TextView>(R.id.no_data_available).visibility = View.VISIBLE
                rootView.findViewById<TextView>(R.id.loading_data).visibility = View.GONE
                rootView.findViewById<TextView>(R.id.not_logged_in).visibility = View.GONE
            }
        }
    }
}