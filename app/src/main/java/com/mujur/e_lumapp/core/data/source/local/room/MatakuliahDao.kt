package com.mujur.e_lumapp.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mujur.e_lumapp.core.data.source.local.entity.MatakuliahEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MatakuliahDao {

    @Query("SELECT * FROM matakuliah")
    fun getAllMatakuliah(): Flow<List<MatakuliahEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMatakuliah(matakuliah: List<MatakuliahEntity>)

}