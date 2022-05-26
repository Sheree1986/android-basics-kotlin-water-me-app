package com.example.waterme.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.waterme.BaseApplication
import com.example.waterme.ui.c_u_d.CreateUpdateDeleteViewModel
import com.example.waterme.ui.home.HomeViewModel

class ViewModelFactory(private val application: BaseApplication) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(
                application.injectRepository(), application
            ) as T
            modelClass.isAssignableFrom(CreateUpdateDeleteViewModel::class.java) ->
                CreateUpdateDeleteViewModel(application.injectRepository()) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}