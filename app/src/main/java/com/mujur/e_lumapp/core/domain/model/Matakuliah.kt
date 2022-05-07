package com.mujur.e_lumapp.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Matakuliah(
    val id: Int,
    val kodeMK: Int,
    val nameMK: String,
    val semester: Int,
    val bobot: Int
) : Parcelable
