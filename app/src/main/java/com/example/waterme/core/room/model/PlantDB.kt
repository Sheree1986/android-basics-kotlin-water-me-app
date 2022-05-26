package com.example.waterme.core.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plant")
data class PlantDB(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val schedule: String,
    val type: String,
    val description: String
)