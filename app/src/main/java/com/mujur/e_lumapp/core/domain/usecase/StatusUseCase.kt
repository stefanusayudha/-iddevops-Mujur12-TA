package com.mujur.e_lumapp.core.domain.usecase

import com.mujur.e_lumapp.core.data.common.utils.WrappedListResponse
import com.mujur.e_lumapp.core.data.source.remote.response.StatusResponse
import com.mujur.e_lumapp.core.domain.base.BaseResult
import com.mujur.e_lumapp.core.domain.model.StatusEntity
import com.mujur.e_lumapp.core.domain.repository.IStatusRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StatusUseCase @Inject constructor(private val iStatusRepository: IStatusRepository) {
    suspend fun invoke(id: Int): Flow<BaseResult<List<StatusEntity>, WrappedListResponse<StatusResponse>>> {
        return iStatusRepository.getStatusById(id)
    }
}