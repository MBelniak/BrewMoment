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
import com.rubik.brewmoment.view_model.MyBrewResultsViewModel
import com.rubik.brewmoment.view_model.MyBrewResultsViewModelFactory

class MyBrewResultsFragment : Fragment() {

    private lateinit var myBrewResultsViewModel: MyBrewResultsViewModel
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

        myBrewResultsViewModel = ViewModelProviders.of(this,
            MyBrewResultsViewModelFactory(this.activity!!.application,
                LoginUtil.getCurrentUserEmail())).get(MyBrewResultsViewModel::class.java)

        recyclerView.adapter = BrewResultsRecyclerViewAdapter(
            myBrewResultsViewModel.getResults(),activity!!.applicationContext)

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

        myBrewResultsViewModel.results.observe(this, Observer {
            (recyclerView.adapter as BrewResultsRecyclerViewAdapter).resultsDataset = myBrewResultsViewModel.getResults()
            (recyclerView.adapter as BrewResultsRecyclerViewAdapter).notifyDataSetChanged()
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
        }
        else
        {
            val results = myBrewResultsViewModel.getResults()
            if (results.isEmpty())
            {
                recyclerView.visibility = View.GONE
                rootView.findViewById<TextView>(R.id.no_data_available).visibility = View.VISIBLE
                rootView.findViewById<TextView>(R.id.not_logged_in).visibility = View.GONE
            }
            else
            {
                recyclerView.visibility = View.VISIBLE
                rootView.findViewById<TextView>(R.id.no_data_available).visibility = View.GONE
                rootView.findViewById<TextView>(R.id.not_logged_in).visibility = View.GONE
            }
        }
    }
}