package com.mujur.e_lumapp.core.data.source.local

import com.mujur.e_lumapp.core.data.source.local.entity.MatakuliahEntity
import com.mujur.e_lumapp.core.data.source.local.room.MatakuliahDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val matakuliahDao: MatakuliahDao) {

    fun getAllMatakuliah(): Flow<List<MatakuliahEntity>> = matakuliahDao.getAllMatakuliah()

    suspend fun insertMatakuliah(matakuliahList: List<MatakuliahEntity>) =
        matakuliahDao.insertMatakuliah(matakuliahList)
}