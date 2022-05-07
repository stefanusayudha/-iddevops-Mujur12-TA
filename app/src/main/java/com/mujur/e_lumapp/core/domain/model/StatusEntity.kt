package com.mujur.e_lumapp.core.domain.model

data class StatusEntity(
    val code: String,
    val data: List<StatusResultEntity>,
    val message: String?
)
