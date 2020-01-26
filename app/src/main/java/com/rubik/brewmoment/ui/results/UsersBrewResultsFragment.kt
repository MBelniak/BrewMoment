package com.rubik.brewmoment.ui.results

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rubik.brewmoment.R
import com.rubik.brewmoment.model.data.BrewResult
import com.rubik.brewmoment.ui.recipes.OnResultItemClickListener
import com.rubik.brewmoment.ui.recipes.ResultsRecyclerViewAdapter
import com.rubik.brewmoment.view_model.UsersBrewResultsViewModel

class UsersBrewResultsFragment : Fragment() {

    private lateinit var usersBrewResultsViewModel: UsersBrewResultsViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_results_list, container, false)

        return rootView
    }

    override fun onStart() {
        super.onStart()
        usersBrewResultsViewModel = ViewModelProviders.of(this).get(UsersBrewResultsViewModel::class.java)
        linearLayoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        recyclerView = rootView.findViewById(R.id.results_recycle_view)

        recyclerView.adapter = ResultsRecyclerViewAdapter(usersBrewResultsViewModel.results.value!!, activity!!.applicationContext)

        (recyclerView.adapter as ResultsRecyclerViewAdapter).setOnItemClickListener(object :
            OnResultItemClickListener {
            override fun onItemClick(brewResult: BrewResult) {
                val intent = Intent(activity, ResultDetailsActivity::class.java)
                val bundle = Bundle()
                bundle.putString("ResultKey", brewResult.key)
                bundle.putBoolean("IsMyResult", false)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        })

        recyclerView.layoutManager = linearLayoutManager
        recyclerView.setHasFixedSize(true)
    }
}