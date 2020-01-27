package com.rubik.brewmoment.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import android.app.Application



class MyRecipesViewModelFactory(val application: Application, val email: String) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MyRecipesViewModel(application = application, email = email) as T
    }

}