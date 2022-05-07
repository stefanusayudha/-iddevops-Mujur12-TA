package com.mujur.e_lumapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class LoginRequest(

    @SerializedName("nim_nidn")
    val nim_nidn: String,
    @SerializedName("password")
    val password: String,
)
