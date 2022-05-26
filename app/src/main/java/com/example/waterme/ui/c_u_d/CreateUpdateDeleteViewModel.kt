package com.example.waterme.ui.c_u_d

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.waterme.core.model.Plant
import com.example.waterme.core.repository.Repository
import kotlinx.coroutines.launch

class CreateUpdateDeleteViewModel(private val repository: Repository) : ViewModel() {
    fun insertPlant(plant: Plant) = viewModelScope.launch { repository.insertPlant(plant) }
    fun getPlant(plantId: Int) = repository.getPlant(plantId).asLiveData()
    fun updatePlant(plant: Plant) = viewModelScope.launch { repository.updatePlant(plant) }
    fun deletePlant(plant: Plant) = viewModelScope.launch { repository.deletePlant(plant) }
}