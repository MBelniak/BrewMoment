package com.rubik.brewmoment.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.rubik.brewmoment.model.data.BrewResult
import com.rubik.brewmoment.model.data.BrewResultsDAO

class UsersBrewResultsViewModel(application: Application) : AndroidViewModel(application){
    var results: LiveData<List<BrewResult>> = BrewResultsDAO.getAllUsersResults()

    fun getResults(): List<BrewResult> {
        return results.value ?: listOf()
    }
}
