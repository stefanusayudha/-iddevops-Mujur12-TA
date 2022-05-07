package com.mujur.e_lumapp.ui.mhs.qrcode

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter
import com.mujur.e_lumapp.R
import com.mujur.e_lumapp.TES.GeneralView
import com.mujur.e_lumapp.TES.Presenter
import com.mujur.e_lumapp.core.data.source.remote.response.StatusResponse
import com.mujur.e_lumapp.core.domain.model.StatusEntity
import com.mujur.e_lumapp.core.utils.SharedPrefs
import com.mujur.e_lumapp.databinding.FragmentQrcodeBinding
import com.mujur.e_lumapp.login.ui.common.extension.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class QrcodeFragment : Fragment() {
    private lateinit var presenter: Presenter


    private var statusList: MutableList<StatusResponse> = mutableListOf()

    private val qrCodeViewModel: QRCodeViewModel by viewModels()

    lateinit var iv: ImageView
    private var _binding: FragmentQrcodeBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var sharedPrefs: SharedPrefs

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentQrcodeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        iv = view.findViewById(R.id.iv_qrcode)
        loadDataFromAPI()

        fetchCurrentStatus()
        observeStatus()
        observeState()
    }


    private fun fetchCurrentStatus() {
        val id = sharedPrefs.getId()
        Log.d("ID_STATUS", "$id")
        if (id != 0) {
            qrCodeViewModel.getStatus(id)
        }
    }

    private fun observeState() {
        qrCodeViewModel.state.flowWithLifecycle(viewLifecycleOwner.lifecycle,
            Lifecycle.State.STARTED)
            .onEach { state -> handleState(state) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun handleState(state: QRCodeFragmentState) {
        when (state) {
            is QRCodeFragmentState.Init -> Unit
            is QRCodeFragmentState.ShowToast -> requireActivity().showToast(state.message)
            is QRCodeFragmentState.IsLoading -> handleLoading(state.isLoading)
        }
    }

    private fun handleLoading(loading: Boolean) {
        binding.pbQrcode.isIndeterminate = loading
        if (!loading) {
            binding.pbQrcode.progress = 0
        }
    }

    private fun observeStatus() {
        qrCodeViewModel.status.flowWithLifecycle(viewLifecycleOwner.lifecycle,
            Lifecycle.State.STARTED)
            .onEach { status ->
                status?.let {
                    handleStatus(it)
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun handleStatus(status: StatusEntity){

        val data = status.data.forEach { statusResult ->
            statusResult.status
        }

        val status = data.toString().toBoolean()
        sharedPrefs.saveStatus(status)

        if (!status) {
            binding.viewError.root.visibility = View.VISIBLE
        } else {
            generateQrcode()
        }
    }

    private fun loadDataFromAPI() {
        presenter = Presenter(this, requireContext())
        val id = sharedPrefs.getId()
        Log.d("ID_STATUS", "$id")
        presenter.getById(id)
    }


    private fun generateQrcode() {
        val name = "Budi Santoso"
        val nim = "10203040"

        val data = "$name + $nim"

        val writer = QRCodeWriter()
        try {
            val bitMatrix = writer.encode(data, BarcodeFormat.QR_CODE, 512, 512)
            val width = bitMatrix.width
            val heigth = bitMatrix.height
            val bmp = Bitmap.createBitmap(width, heigth, Bitmap.Config.RGB_565)

            for (x in 0 until width) {
                for (y in 0 until heigth) {
                    bmp.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
                }
            }
            iv.setImageBitmap(bmp)
        } catch (e: WriterException) {
            e.printStackTrace()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}