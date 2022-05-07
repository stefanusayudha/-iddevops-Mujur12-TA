package com.mujur.e_lumapp.core.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "matakuliah")
data class MatakuliahEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "kode_matakuliah")
    var kodeMK: Int,

    @ColumnInfo(name = "nama_matakuliah")
    var nameMK: String,

    @ColumnInfo(name = "semester")
    var semester: Int,

    @ColumnInfo(name = "bobot")
    var bobot: Int

) : Parcelable