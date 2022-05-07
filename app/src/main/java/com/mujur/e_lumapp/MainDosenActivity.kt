package com.mujur.e_lumapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.mujur.e_lumapp.databinding.ActivityDosenMainBinding
import com.mujur.e_lumapp.ui.dosen.home.HomeDosenFragment
import com.mujur.e_lumapp.ui.dosen.presensi.PresensiFragment
import com.mujur.e_lumapp.ui.dosen.profile.ProfileDosenFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainDosenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDosenMainBinding
    lateinit var homeDosenFragment: HomeDosenFragment
    lateinit var presensiFragment: PresensiFragment
    lateinit var profileDosenFragment: ProfileDosenFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDosenMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        homeDosenFragment = HomeDosenFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout, homeDosenFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()

        binding.btnNavDosen.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home_menu_dosen -> {
                    homeDosenFragment = HomeDosenFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, homeDosenFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .commit()
                }
                R.id.menu_presensi -> {
                    presensiFragment = PresensiFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, presensiFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .commit()
                }
                R.id.menu_profile_dosen -> {
                    profileDosenFragment = ProfileDosenFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, profileDosenFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .commit()
                }
            }
            true
        }
    }
}