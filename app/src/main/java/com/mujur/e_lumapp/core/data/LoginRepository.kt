package com.mujur.e_lumapp.core.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mujur.e_lumapp.core.data.common.utils.WrappedResponse
import com.mujur.e_lumapp.core.data.source.remote.network.ApiService
import com.mujur.e_lumapp.core.data.source.remote.response.LoginRequest
import com.mujur.e_lumapp.core.data.source.remote.response.LoginResult
import com.mujur.e_lumapp.core.domain.base.BaseResult
import com.mujur.e_lumapp.core.domain.model.Login
import com.mujur.e_lumapp.core.domain.repository.ILoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginRepository @Inject constructor(private val apiService: ApiService) : ILoginRepository {
    override suspend fun login(loginRequest: LoginRequest): Flow<BaseResult<Login, WrappedResponse<LoginResult>>> {
        return flow {
            val response = apiService.login(loginRequest)
            if (response.isSuccessful) {
                val body = response.body()!!
                val loginEntity = Login(
                    body.data?.isAdmin!!,
                    body.data?.nameMhsDosen!!,
                    body.data?.id!!,
                    body.data?.nimNidn!!,
                    body.data?.token!!,
                )
                emit(BaseResult.Success(loginEntity))
            }else {
                val type = object : TypeToken<WrappedResponse<LoginResult>>(){}.type
                val err : WrappedResponse<LoginResult> = Gson().fromJson(response.errorBody()!!.charStream(), type)
                err.code = response.code()
                emit(BaseResult.Error(err))
            }
        }
    }
}