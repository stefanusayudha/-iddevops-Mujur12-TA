package com.mujur.e_lumapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListMatakuliahResponse(
    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("data")
    val data: List<MatakuliahResponse>
)
