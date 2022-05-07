package com.mujur.e_lumapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("code")
	val code: String,

	@field:SerializedName("data")
	val data: LoginResult,

	@field:SerializedName("message")
	val message: String
)


