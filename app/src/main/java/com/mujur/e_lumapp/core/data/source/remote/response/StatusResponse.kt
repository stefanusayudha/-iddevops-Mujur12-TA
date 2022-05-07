package com.mujur.e_lumapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class StatusResponse(

    @field:SerializedName("code")
    val code: String,

    @field:SerializedName("data")
    val data: List<StatusResult>,

    @field:SerializedName("message")
    val message: String,
)

data class StatusResult(

    @field:SerializedName("login_id")
    val loginId: Int,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("status")
    val status: Boolean,
)
