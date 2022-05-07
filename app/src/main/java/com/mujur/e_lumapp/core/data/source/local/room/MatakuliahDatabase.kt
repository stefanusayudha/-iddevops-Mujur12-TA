package com.mujur.e_lumapp.core.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mujur.e_lumapp.core.data.source.local.entity.MatakuliahEntity

@Database(entities = [MatakuliahEntity::class], version = 1, exportSchema = false)
abstract class MatakuliahDatabase : RoomDatabase() {

    abstract fun matakuliahDao(): MatakuliahDao

    companion object {
        @Volatile
        private var INSTANCE: MatakuliahDatabase? = null

        fun getInstance(context: Context): MatakuliahDatabase =
            INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MatakuliahDatabase::class.java,
                    "Matakuliah.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
    }
}