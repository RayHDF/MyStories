package com.rayhdf.mystories.ui.auth.login

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.rayhdf.mystories.databinding.ActivityLoginBinding
import com.rayhdf.mystories.data.api.RetrofitInstance
import com.rayhdf.mystories.data.repository.AuthRepository
import com.rayhdf.mystories.ui.auth.AuthViewModelFactory

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val authRepository = AuthRepository(RetrofitInstance.api)

        loginViewModel = ViewModelProvider(this, AuthViewModelFactory(authRepository))
            .get(LoginViewModel::class.java)

        binding.btnRegister.setOnClickListener {
            val email = binding.edLoginEmail.text.toString()
            val password = binding.edLoginPassword.text.toString()
            loginViewModel.login(email, password)
        }

        loginViewModel.loginResult.observe(this) { result ->
            if (result != null) {
                Log.d("LoginActivity", "Login response: $result")
                if (result.error == true) {
                    Toast.makeText(this, "Error: ${result.message}", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Login successful!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}