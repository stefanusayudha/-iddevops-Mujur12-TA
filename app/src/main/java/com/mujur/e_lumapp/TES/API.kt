package com.mujur.e_lumapp.TES

import com.mujur.e_lumapp.core.data.source.remote.response.StatusResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface API {
    @GET("status/{id}")
    fun getStatusById(@Path("id") id: Int): Deferred<StatusResponse>
}