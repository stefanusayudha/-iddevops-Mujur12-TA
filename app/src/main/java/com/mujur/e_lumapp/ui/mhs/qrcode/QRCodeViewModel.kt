package com.mujur.e_lumapp.ui.mhs.qrcode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mujur.e_lumapp.core.domain.base.BaseResult
import com.mujur.e_lumapp.core.domain.model.StatusEntity
import com.mujur.e_lumapp.core.domain.usecase.StatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QRCodeViewModel @Inject constructor(private var statusUseCase: StatusUseCase) : ViewModel() {

    private val _state = MutableStateFlow<QRCodeFragmentState>(QRCodeFragmentState.Init)
    val state: StateFlow<QRCodeFragmentState> get() = _state

    private val _status = MutableStateFlow<List<StatusEntity>>(mutableListOf())
    val status: StateFlow<List<StatusEntity>> get() = _status

    private fun setLoading() {
        _state.value = QRCodeFragmentState.IsLoading(true)
    }

    private fun hideLoading() {
        _state.value = QRCodeFragmentState.IsLoading(false)
    }

    private fun showToast(message: String) {
        _state.value = QRCodeFragmentState.ShowToast(message)
    }

    fun getStatus(id: Int) {
        viewModelScope.launch {
            statusUseCase.invoke(id)
                .onStart {
                    setLoading()
                }
                .catch { exception ->
                    hideLoading()
                    showToast(exception.message.toString())
                }
                .collect { result ->
                    hideLoading()
                    when(result) {
                        is BaseResult.Success -> {
                            _status.value = result.data
                        }
                        is BaseResult.Success -> {
                            showToast(result.rawResponse.message)
                        }
                    }
                }
        }
    }
}

sealed class QRCodeFragmentState {
    object Init : QRCodeFragmentState()
    data class IsLoading(val isLoading: Boolean) : QRCodeFragmentState()
    data class ShowToast(val message: String) : QRCodeFragmentState()
}