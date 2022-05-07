package com.mujur.e_lumapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MatakuliahResponse(

    @field:SerializedName("id")
    var id: Int,

    @field:SerializedName("kode_matakuliah")
    var kodeMK: Int,

    @field:SerializedName("nama_matakuliah")
    var nameMK: String,

    @field:SerializedName("semester")
    var semester: Int,

    @field:SerializedName("bobot")
    var bobot: Int,
)
