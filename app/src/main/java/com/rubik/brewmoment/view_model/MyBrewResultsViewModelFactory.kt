package com.rubik.brewmoment.view_model

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class MyBrewResultsViewModelFactory(val application: Application, val email: String?) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MyBrewResultsViewModel(application = application, email = email) as T
    }

}