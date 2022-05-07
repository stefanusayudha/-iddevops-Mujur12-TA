package com.mujur.e_lumapp.core.data

import com.mujur.e_lumapp.core.data.source.local.LocalDataSource
import com.mujur.e_lumapp.core.data.source.remote.RemoteDataSource
import com.mujur.e_lumapp.core.data.source.remote.network.ApiResponse
import com.mujur.e_lumapp.core.data.source.remote.response.MatakuliahResponse
import com.mujur.e_lumapp.core.domain.model.Matakuliah
import com.mujur.e_lumapp.core.domain.repository.IMatakuliahRepository
import com.mujur.e_lumapp.core.utils.AppExecutors
import com.mujur.e_lumapp.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MatakuliahRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMatakuliahRepository {

    override fun getAllMatakuliah(): Flow<Resource<List<Matakuliah>>> =
        object :
            NetworkBoundResource<List<Matakuliah>, List<MatakuliahResponse>>() {
            override fun loadFromDB(): Flow<List<Matakuliah>> {
                return localDataSource.getAllMatakuliah().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Matakuliah>?): Boolean =
//                data == null || data.isEmpty()
                true


            override suspend fun createCall(): Flow<ApiResponse<List<MatakuliahResponse>>> =
                remoteDataSource.getAllMatakuliah()

            override suspend fun saveCallResult(data: List<MatakuliahResponse>) {
                val matakuliahList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMatakuliah(matakuliahList)
            }
        }.asFlow()
}