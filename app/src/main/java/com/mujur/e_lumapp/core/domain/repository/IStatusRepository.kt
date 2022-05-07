package com.mujur.e_lumapp.core.domain.repository

import com.mujur.e_lumapp.core.data.common.utils.WrappedListResponse
import com.mujur.e_lumapp.core.data.source.remote.response.StatusResponse
import com.mujur.e_lumapp.core.domain.base.BaseResult
import com.mujur.e_lumapp.core.domain.model.StatusEntity
import kotlinx.coroutines.flow.Flow

interface IStatusRepository {
    suspend fun getStatusById(id : Int) : Flow<BaseResult<List<StatusEntity>, WrappedListResponse<StatusResponse>>>
}