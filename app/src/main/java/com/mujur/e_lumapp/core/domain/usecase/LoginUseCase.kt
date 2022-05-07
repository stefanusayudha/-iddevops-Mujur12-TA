package com.mujur.e_lumapp.core.domain.usecase

import com.mujur.e_lumapp.core.data.LoginRepository
import com.mujur.e_lumapp.core.data.common.utils.WrappedResponse
import com.mujur.e_lumapp.core.data.source.remote.response.LoginRequest
import com.mujur.e_lumapp.core.data.source.remote.response.LoginResult
import com.mujur.e_lumapp.core.domain.base.BaseResult
import com.mujur.e_lumapp.core.domain.model.Login
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginRepository: LoginRepository){
    suspend fun execute(loginRequest: LoginRequest): Flow<BaseResult<Login, WrappedResponse<LoginResult>>> {
        return loginRepository.login(loginRequest)
    }
}