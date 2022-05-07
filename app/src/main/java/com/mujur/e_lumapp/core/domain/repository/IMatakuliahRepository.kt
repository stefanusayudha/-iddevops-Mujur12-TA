package com.mujur.e_lumapp.core.domain.repository

import com.mujur.e_lumapp.core.data.Resource
import com.mujur.e_lumapp.core.domain.model.Matakuliah
import kotlinx.coroutines.flow.Flow

interface IMatakuliahRepository {
    fun getAllMatakuliah(): Flow<Resource<List<Matakuliah>>>
}