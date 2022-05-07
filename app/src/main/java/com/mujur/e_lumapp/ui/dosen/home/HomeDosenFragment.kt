package com.mujur.e_lumapp.ui.dosen.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mujur.e_lumapp.R
import com.mujur.e_lumapp.core.data.Resource
import com.mujur.e_lumapp.core.ui.AllMatakuliahAdapter
import com.mujur.e_lumapp.core.utils.SharedPrefs
import com.mujur.e_lumapp.databinding.FragmentHomeDosenBinding
import com.mujur.e_lumapp.ui.dosen.detail.DetailAllMatakuliahActivity
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*
import java.util.prefs.Preferences
import javax.inject.Inject

@AndroidEntryPoint
class HomeDosenFragment : Fragment() {

    private val homeDosenViewModel: HomeDosenViewModel by viewModels()

    private var _binding: FragmentHomeDosenBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var sharedPrefs: SharedPrefs

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeDosenBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val allMatakuliahAdapter = AllMatakuliahAdapter()
            allMatakuliahAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailAllMatakuliahActivity::class.java)
                intent.putExtra(DetailAllMatakuliahActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            homeDosenViewModel.matakuliah.observe(viewLifecycleOwner, { matakuliah ->
                if (matakuliah != null) {
                    when (matakuliah) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            allMatakuliahAdapter.setData(matakuliah.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewErrorMatakuliah.root.visibility = View.VISIBLE
                            binding.viewErrorMatakuliah.tvError.text =
                                matakuliah.message ?: getString(
                                    R.string.something_wrong
                                )
                        }
                    }
                }
            })

            with(binding.rvAllmatakuliah) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = allMatakuliahAdapter
            }
        }

        val date = Calendar.getInstance().time
        val sdf = SimpleDateFormat("dd MMMM yyyy")
        val formatedDate = sdf.format(date)
        binding.txtDateDosen.text = formatedDate.toString()

        val data = sharedPrefs.getName()
        binding.txtLoginName.text = "Hello, $data"
    }
}