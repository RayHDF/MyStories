package com.rayhdf.mystories.ui.auth.register

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.rayhdf.mystories.databinding.ActivityRegisterBinding
import com.rayhdf.mystories.data.api.RetrofitInstance
import com.rayhdf.mystories.data.repository.AuthRepository
import com.rayhdf.mystories.ui.auth.AuthViewModelFactory

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val authRepository = AuthRepository(RetrofitInstance.api)

        registerViewModel = ViewModelProvider(this, AuthViewModelFactory(authRepository))
            .get(RegisterViewModel::class.java)

        binding.btnRegister.setOnClickListener {
            val name = binding.edRegisterName.text.toString()
            val email = binding.edRegisterEmail.text.toString()
            val password = binding.edRegisterPassword.text.toString()
            registerViewModel.register(name, email, password)
        }

        registerViewModel.registerResult.observe(this) { result ->
            if (result != null) {
                Log.d("RegisterActivity", "Register response: $result")
                if (result.error == true) {
                    Toast.makeText(this, "Error: ${result.message}", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Registration successful!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}