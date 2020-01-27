package com.rubik.brewmoment.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.rubik.brewmoment.model.data.BrewResult
import com.rubik.brewmoment.model.data.BrewResultsDAO

class BrewResultsViewModel(application: Application) : AndroidViewModel(application) {

    var results: LiveData<List<BrewResult>> = BrewResultsDAO.getAllResults()

    fun getMyResults(email: String?): List<BrewResult> {
        if (email != null)
            return results.value?.filter { it.authorEmail == email} ?: listOf()
        return listOf()
    }

    fun getSharedResults(email: String?): List<BrewResult> {
        return results.value?.filter { it.isResultShared || it.authorEmail == email} ?: listOf()
    }

    fun getResult(key: String): BrewResult?
    {
        return BrewResultsDAO.getByKey(key)
    }
}