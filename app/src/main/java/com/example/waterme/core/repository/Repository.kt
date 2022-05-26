package com.example.waterme.core.repository

import com.example.waterme.core.model.Plant
import com.example.waterme.core.room.RoomDao
import com.example.waterme.utils.MapVal
import kotlinx.coroutines.flow.map

class Repository private constructor(private val dao: RoomDao) {
    companion object {
        @Volatile
        private var INSTANCE: Repository? = null

        fun getInstance(dao: RoomDao) = INSTANCE ?: synchronized(this) {
            INSTANCE ?: Repository(dao).apply { INSTANCE = this }
        }
    }

    suspend fun insertPlant(plant: Plant) = dao.insertPlant(MapVal.plantUsetoDB(plant))

    fun getAllPlants() = dao.getAllPlants().map { MapVal.plantDBtoUse(it) }

    fun getPlant(plantId: Int) = dao.getPlant(plantId).map { MapVal.plantDBtoUse(it) }

    suspend fun updatePlant(plant: Plant) = dao.updatePlant(MapVal.plantUsetoDB(plant))

    suspend fun deletePlant(plant: Plant) = dao.deletePlant(MapVal.plantUsetoDB(plant))
}