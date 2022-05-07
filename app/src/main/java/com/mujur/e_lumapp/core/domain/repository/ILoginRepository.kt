package com.mujur.e_lumapp.core.domain.repository

import com.mujur.e_lumapp.core.data.common.utils.WrappedResponse
import com.mujur.e_lumapp.core.data.source.remote.response.LoginRequest
import com.mujur.e_lumapp.core.data.source.remote.response.LoginResult
import com.mujur.e_lumapp.core.domain.base.BaseResult
import com.mujur.e_lumapp.core.domain.model.Login
import kotlinx.coroutines.flow.Flow

interface ILoginRepository {
    suspend fun login(loginRequest: LoginRequest) : Flow<BaseResult<Login, WrappedResponse<LoginResult>>>
}