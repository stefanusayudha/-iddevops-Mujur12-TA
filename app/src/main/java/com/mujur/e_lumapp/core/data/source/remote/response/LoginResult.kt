package com.mujur.e_lumapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResult(

    @field:SerializedName("is_admin")
    val isAdmin: String,

    @field:SerializedName("name_mhs_dosen")
    val nameMhsDosen: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("nim_nidn")
    val nimNidn: Int,

    @field:SerializedName("token")
    val token: String
)

