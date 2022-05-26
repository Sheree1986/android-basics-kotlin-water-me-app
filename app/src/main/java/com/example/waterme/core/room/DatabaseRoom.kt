package com.example.waterme.core.room

import android.content.ContentValues
import android.content.Context
import androidx.room.Database
import androidx.room.OnConflictStrategy
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.waterme.core.room.dummy.DataSource
import com.example.waterme.core.room.model.PlantDB

@Database(entities = [PlantDB::class], version = 1, exportSchema = false)
abstract class DatabaseRoom : RoomDatabase() {
    companion object {
        @Volatile
        private var INSTANCE: DatabaseRoom? = null

        fun getInstance(context: Context) = INSTANCE ?: synchronized(this) {
            INSTANCE ?: Room.databaseBuilder(context, DatabaseRoom::class.java, "PlantDB")
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        val plants = DataSource.plants
                        for (plant in plants) {
                            db.insert("plant", OnConflictStrategy.REPLACE, ContentValues().apply {
                                put("id", plant.id)
                                put("name", plant.name)
                                put("schedule", plant.schedule)
                                put("type", plant.type)
                                put("description", plant.description)
                            })
                        }
                    }
                }).fallbackToDestructiveMigration().build().apply { INSTANCE = this }
        }
    }

    abstract fun generateDao(): RoomDao
}