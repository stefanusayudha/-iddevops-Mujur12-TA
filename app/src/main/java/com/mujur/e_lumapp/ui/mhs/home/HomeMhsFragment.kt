package com.mujur.e_lumapp.ui.mhs.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mujur.e_lumapp.core.data.Resource
import com.mujur.e_lumapp.core.ui.MatakuliahAdapter
import com.mujur.e_lumapp.core.utils.SharedPrefs
import com.mujur.e_lumapp.databinding.FragmentHomeMhsBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class HomeMhsFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeMhsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var sharedPrefs: SharedPrefs

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeMhsBinding.inflate(layoutInflater)
        return binding.root
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val matakuliahAdapter = MatakuliahAdapter()
            homeViewModel.matakuliah.observe(viewLifecycleOwner, { matakuliah ->
                if (matakuliah != null) {
                    when (matakuliah) {
                        is Resource.Success -> {
                            matakuliahAdapter.setData(matakuliah.data)
                        }
                    }
                }
            })
            with(binding.rvMatakuliah) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = matakuliahAdapter
            }
        }

        val date = Calendar.getInstance().time
        val sdf = SimpleDateFormat("dd MMMM yyyy")
        val formatedDate = sdf.format(date)
        binding.date.text = formatedDate.toString()

        val data = sharedPrefs.getName()
        binding.txtNameLogin.text = "Hello, $data"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}