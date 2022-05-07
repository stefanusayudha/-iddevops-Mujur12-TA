package com.mujur.e_lumapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.mujur.e_lumapp.databinding.ActivityMhsMainBinding
import com.mujur.e_lumapp.ui.mhs.home.HomeMhsFragment
import com.mujur.e_lumapp.ui.mhs.profile.ProfileMhsFragment
import com.mujur.e_lumapp.ui.mhs.qrcode.QrcodeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainMhsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMhsMainBinding
    lateinit var homeMhsFragment: HomeMhsFragment
    lateinit var qrcodeFragment: QrcodeFragment
    lateinit var profileMhsFragment: ProfileMhsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMhsMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        homeMhsFragment = HomeMhsFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout, homeMhsFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()

        binding.btnNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home_menu -> {
                    homeMhsFragment = HomeMhsFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, homeMhsFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .commit()
                }
                R.id.qrcode_menu -> {
                    qrcodeFragment = QrcodeFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, qrcodeFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .commit()
                }
                R.id.profile_menu -> {
                    profileMhsFragment = ProfileMhsFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, profileMhsFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .commit()
                }
            }
            true
        }
    }
}