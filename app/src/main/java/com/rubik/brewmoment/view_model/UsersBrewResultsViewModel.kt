package com.rubik.brewmoment.view_model

import android.app.Application
import android.os.SystemClock
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rubik.brewmoment.model.data.BrewResult
import com.rubik.brewmoment.model.data.BrewResultRepository
import java.sql.Date

class UsersBrewResultsViewModel(application: Application) : AndroidViewModel(application){
    private var repositoryBrew: BrewResultRepository = BrewResultRepository()
    val results: LiveData<List<BrewResult>> = getAllResults()

    private fun getAllResults(): LiveData<List<BrewResult>> {
        val result1 = BrewResult(2, 50, "Kostaryka La Candelilla",
            "Acidic", true, Date(SystemClock.elapsedRealtime()),
            isRecipeDefault = true,
            isResultShared = false
        )
        val result2 = BrewResult(2, 50, "Kostaryka La Candelilla",
            "Acidic", false, Date(SystemClock.elapsedRealtime()),
            isRecipeDefault = true,
            isResultShared = false)
        val data = MutableLiveData<List<BrewResult>>()
        data.value = listOf(result1, result2)
        return data
    }
}
