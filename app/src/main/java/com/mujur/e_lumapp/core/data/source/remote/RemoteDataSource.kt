package com.mujur.e_lumapp.core.data.source.remote

import android.util.Log
import com.mujur.e_lumapp.core.data.source.remote.network.ApiResponse
import com.mujur.e_lumapp.core.data.source.remote.network.ApiService
import com.mujur.e_lumapp.core.data.source.remote.response.MatakuliahResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getAllMatakuliah(): Flow<ApiResponse<List<MatakuliahResponse>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getList()
                val dataArray = response.data
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}