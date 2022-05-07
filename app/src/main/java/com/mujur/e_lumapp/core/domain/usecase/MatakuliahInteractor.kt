package com.mujur.e_lumapp.core.domain.usecase

import com.mujur.e_lumapp.core.domain.repository.IMatakuliahRepository
import javax.inject.Inject

class MatakuliahInteractor @Inject constructor(private val matakuliahRepository: IMatakuliahRepository) :
    MatakuliahUseCase {

    override fun getAllMatakuliah() = matakuliahRepository.getAllMatakuliah()
}