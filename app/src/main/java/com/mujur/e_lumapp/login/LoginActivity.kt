package com.mujur.e_lumapp.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.mujur.e_lumapp.MainDosenActivity
import com.mujur.e_lumapp.MainMhsActivity
import com.mujur.e_lumapp.core.data.common.utils.WrappedResponse
import com.mujur.e_lumapp.core.data.source.remote.response.LoginRequest
import com.mujur.e_lumapp.core.data.source.remote.response.LoginResult
import com.mujur.e_lumapp.core.domain.model.Login
import com.mujur.e_lumapp.core.utils.SharedPrefs
import com.mujur.e_lumapp.databinding.ActivityLoginBinding
import com.mujur.e_lumapp.login.ui.common.extension.showGenericAlertDialog
import com.mujur.e_lumapp.login.ui.common.extension.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()


    @Inject
    lateinit var sharedPrefs: SharedPrefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observe()
        login()
    }

    private fun login() {
        binding.btnLogin.setOnClickListener {
            val username = binding.txtUsername.text.toString().trim()
            val password = binding.txtPassword.text.toString().trim()

            if (validate(username, password)) {
                val loginRequest = LoginRequest(username, password)
                viewModel.login(loginRequest)
                binding.pbLogin.visibility = View.VISIBLE
            }
        }
    }

    private fun validate(username: String, password: String): Boolean {
        resetAllInputError()
        if (username.isEmpty()) {
            setUsernameError("Inputan username tidak boleh kosong")
            return false
        }

        if (password.length < 8) {
            setPasswordError("Password minimal 8")
            return false
        }
        return true
    }

    private fun resetAllInputError() {
        setUsernameError(null)
        setPasswordError(null)
    }

    private fun setUsernameError(e: String?) {
        binding.txtUsername.error = e
    }

    private fun setPasswordError(e: String?) {
        binding.txtPassword.error = e
    }

    private fun observe() {
        viewModel.mState
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { state -> handleStateChange(state) }
            .launchIn(lifecycleScope)
    }

    private fun handleStateChange(state: LoginActivityState) {
        when (state) {
            is LoginActivityState.Init -> Unit
            is LoginActivityState.ErrorLogin -> handleErrorLogin(state.rawResponse)
            is LoginActivityState.SuccessLogin -> handleSuccessLogin(state.loginEntity)
            is LoginActivityState.ShowToast -> showToast(state.message)
            is LoginActivityState.IsLoading -> handleLoading(state.isLoading)
        }
    }

    private fun handleErrorLogin(response: WrappedResponse<LoginResult>) {
        showGenericAlertDialog(response.message)
    }

    private fun handleLoading(isLoading: Boolean) {
        binding.btnLogin.isEnabled = !isLoading
        binding.pbLogin.isIndeterminate = isLoading
        if (!isLoading) {
            binding.pbLogin.progress = 0
        }
    }

    private fun handleSuccessLogin(loginEntity: Login) {
        binding.pbLogin.visibility = View.GONE

        with(sharedPrefs) {
            saveToken(loginEntity.token)
            saveIsAdmin(loginEntity.isAdmin)
            saveName(loginEntity.nameMhsDosen)
            saveID(loginEntity.id)
        }

        showToast("Welcome ${loginEntity.nameMhsDosen}")
        val isAdmin = loginEntity.isAdmin

        if (isAdmin == "0") {
            val intent = Intent(this@LoginActivity, MainMhsActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            val intent = Intent(this@LoginActivity, MainDosenActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}