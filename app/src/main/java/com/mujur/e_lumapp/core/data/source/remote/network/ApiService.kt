package com.mujur.e_lumapp.core.data.source.remote.network

import com.mujur.e_lumapp.core.data.common.utils.WrappedResponse
import com.mujur.e_lumapp.core.data.source.remote.response.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<WrappedResponse<LoginResult>>

    @GET("matakuliah")
    suspend fun getList(): ListMatakuliahResponse



    @GET("filter/{id}")
    suspend fun getMatakuliahById(@Path("id") id: String): ListMatakuliahResponse
}