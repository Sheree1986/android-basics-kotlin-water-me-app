package com.example.waterme.utils

import com.example.waterme.core.model.Plant
import com.example.waterme.core.room.model.PlantDB

object MapVal {
    fun plantDBtoUse(plantDB: PlantDB?): Plant? {
        var plant: Plant? = null
        plantDB?.apply {
            plant = Plant(id, name, schedule, type, description)
        }
        return plant
    }

    fun plantDBtoUse(list: List<PlantDB?>) = list.map {
        println(it)
        plantDBtoUse(it)
    }

    fun plantUsetoDB(plant: Plant): PlantDB {
        plant.apply { return PlantDB(id, name, schedule, type, description) }
    }

    fun plantUsetoDB(list: List<Plant>) = list.map { plantUsetoDB(it) }
}