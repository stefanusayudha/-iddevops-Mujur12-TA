package com.mujur.e_lumapp.core.domain.model

data class Login(
    val isAdmin: String,
    val nameMhsDosen: String,
    val id: Int,
    val nimNidn: Int,
    val token: String,
)