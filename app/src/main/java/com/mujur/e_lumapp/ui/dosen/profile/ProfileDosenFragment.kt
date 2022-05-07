package com.mujur.e_lumapp.ui.dosen.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mujur.e_lumapp.core.utils.SharedPrefs
import com.mujur.e_lumapp.databinding.FragmentProfileDosenBinding
import com.mujur.e_lumapp.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileDosenFragment : Fragment() {

    private var _binding: FragmentProfileDosenBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var sharedPrefs: SharedPrefs

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileDosenBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogoutDosen.setOnClickListener {
            sharedPrefs.clear()
            startActivity(Intent(requireActivity(), LoginActivity::class.java))
            activity?.finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}