package com.mujur.e_lumapp.core.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mujur.e_lumapp.core.data.common.utils.WrappedListResponse
import com.mujur.e_lumapp.core.data.source.remote.network.ApiStatus
import com.mujur.e_lumapp.core.data.source.remote.response.StatusResponse
import com.mujur.e_lumapp.core.domain.base.BaseResult
import com.mujur.e_lumapp.core.domain.model.StatusEntity
import com.mujur.e_lumapp.core.domain.model.StatusResultEntity
import com.mujur.e_lumapp.core.domain.repository.IStatusRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class StatusRepository @Inject constructor(private val apiStatus: ApiStatus) : IStatusRepository {
    override suspend fun getStatusById(id: Int): Flow<BaseResult<List<StatusEntity>, WrappedListResponse<StatusResponse>>> {
        return flow {
            val response = apiStatus.getStatus(id)
            if (response.isSuccessful) {
                val body = response.body()!!
                val mStatus = mutableListOf<StatusEntity>()
                var dataEntity: StatusResultEntity?

//                body.data?.forEach { statusResponse ->
//                    dataEntity = StatusResultEntity(statusResponse.data.forEach { result ->
//                        result.loginId
//                    })
//                }
                emit(BaseResult.Success(mStatus))
            } else {
                val type = object : TypeToken<WrappedListResponse<StatusResponse>>() {}.type
                val err =
                    Gson().fromJson<WrappedListResponse<StatusResponse>>(response.errorBody()!!
                        .charStream(), type)!!
                err.code = response.code()
                emit(BaseResult.Error(err))
            }
        }
    }
}