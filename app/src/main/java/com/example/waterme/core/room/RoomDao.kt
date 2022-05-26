package com.example.waterme.core.room

import androidx.room.*
import com.example.waterme.core.room.model.PlantDB
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomDao {
    @Insert(entity = PlantDB::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlant(plantDB: PlantDB)

    @Query("SELECT * FROM plant")
    fun getAllPlants(): Flow<List<PlantDB>>

    @Query("SELECT * FROM plant WHERE id=:plantId")
    fun getPlant(plantId: Int): Flow<PlantDB>

    @Update(entity = PlantDB::class)
    suspend fun updatePlant(plantDB: PlantDB)

    @Delete(entity = PlantDB::class)
    suspend fun deletePlant(plantDB: PlantDB)
}