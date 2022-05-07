package com.mujur.e_lumapp.core.utils

import com.mujur.e_lumapp.core.data.source.local.entity.MatakuliahEntity
import com.mujur.e_lumapp.core.data.source.remote.response.MatakuliahResponse
import com.mujur.e_lumapp.core.domain.model.Matakuliah

object DataMapper {
    fun mapResponsesToEntities(input: List<MatakuliahResponse>): List<MatakuliahEntity> {
        val matakuliahList = ArrayList<MatakuliahEntity>()
        input.map {
            val matakuliah = MatakuliahEntity(
                id = it.id,
                kodeMK = it.kodeMK,
                nameMK = it.nameMK,
                semester = it.semester,
                bobot = it.bobot
            )
            matakuliahList.add(matakuliah)
        }
        return matakuliahList
    }

    fun mapEntitiesToDomain(input: List<MatakuliahEntity>): List<Matakuliah> =
        input.map {
            Matakuliah(
                id = it.id,
                kodeMK = it.kodeMK,
                nameMK = it.nameMK,
                semester = it.semester,
                bobot = it.bobot
            )
        }

    fun mapDomainToEntity(input: Matakuliah) = MatakuliahEntity(
        id = input.id,
        kodeMK = input.kodeMK,
        nameMK = input.nameMK,
        semester = input.semester,
        bobot = input.bobot
    )
}