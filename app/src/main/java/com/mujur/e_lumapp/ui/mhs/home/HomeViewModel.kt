package com.mujur.e_lumapp.ui.mhs.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mujur.e_lumapp.core.domain.usecase.MatakuliahUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(matakuliahUseCase: MatakuliahUseCase) : ViewModel() {
    val matakuliah = matakuliahUseCase.getAllMatakuliah().asLiveData()

}