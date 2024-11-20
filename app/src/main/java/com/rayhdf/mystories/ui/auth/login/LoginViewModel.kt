package com.rayhdf.mystories.ui.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rayhdf.mystories.data.api.LoginResponse
import com.rayhdf.mystories.data.repository.AuthRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val authRepository: AuthRepository) : ViewModel() {

    private val _loginResult = MutableLiveData<LoginResponse?>()
    val loginResult : LiveData<LoginResponse?> = _loginResult

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val response = authRepository.login(email, password)
                _loginResult.value = response
            } catch (e: Exception) {
                _loginResult.value = LoginResponse(error = true, message = e.message)
            }
        }
    }
}