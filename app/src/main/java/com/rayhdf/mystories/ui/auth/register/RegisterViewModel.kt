package com.rayhdf.mystories.ui.auth.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rayhdf.mystories.data.api.RegisterResponse
import com.rayhdf.mystories.data.repository.AuthRepository
import kotlinx.coroutines.launch

class RegisterViewModel(private val authRepository: AuthRepository) : ViewModel() {

    private val _registerResult = MutableLiveData<RegisterResponse?>()
    val registerResult: LiveData<RegisterResponse?> = _registerResult

    fun register(name: String, email: String, password: String) {
        viewModelScope.launch {
            try {
                val response = authRepository.register(name, email, password)
                _registerResult.value = response
            } catch (e: Exception) {
                _registerResult.value = RegisterResponse(error = true, message = e.message)
            }
        }
    }
}