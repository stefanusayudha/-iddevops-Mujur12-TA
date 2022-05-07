package com.mujur.e_lumapp.core.data.source.remote.network

import com.mujur.e_lumapp.core.data.common.utils.WrappedListResponse
import com.mujur.e_lumapp.core.data.source.remote.response.StatusResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiStatus {
    @GET("status/{id}")
    suspend fun getStatus(@Path("id") id: Int): Response<WrappedListResponse<StatusResponse>>
}