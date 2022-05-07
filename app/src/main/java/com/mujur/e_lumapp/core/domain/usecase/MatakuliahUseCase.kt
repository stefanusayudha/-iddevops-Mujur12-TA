package com.mujur.e_lumapp.core.domain.usecase

import com.mujur.e_lumapp.core.data.Resource
import com.mujur.e_lumapp.core.data.source.remote.network.ApiResponse
import com.mujur.e_lumapp.core.domain.model.Matakuliah
import kotlinx.coroutines.flow.Flow

interface MatakuliahUseCase {
    fun getAllMatakuliah(): Flow<Resource<List<Matakuliah>>>
}