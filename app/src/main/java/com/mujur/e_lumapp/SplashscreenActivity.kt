package com.mujur.e_lumapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.mujur.e_lumapp.core.utils.SharedPrefs
import com.mujur.e_lumapp.databinding.ActivitySplashscreenBinding
import com.mujur.e_lumapp.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashscreenActivity : AppCompatActivity() {

    @Inject
    lateinit var sharedPrefs: SharedPrefs

    private lateinit var binding: ActivitySplashscreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        splash()
    }

    private fun splash() {
        val token = sharedPrefs.getToken()
        val isAdmin = sharedPrefs.getIsAdmin()
        Handler(Looper.getMainLooper()).postDelayed({
            if (token.isEmpty()) {
                startActivity(Intent(this@SplashscreenActivity, LoginActivity::class.java))
                finish()
            } else {
                if (isAdmin == "0") {
                    startActivity(Intent(this@SplashscreenActivity, MainMhsActivity::class.java))
                    finish()
                } else {
                    startActivity(Intent(this@SplashscreenActivity, MainDosenActivity::class.java))
                    finish()
                }
            }

        }, 2000)
    }
}