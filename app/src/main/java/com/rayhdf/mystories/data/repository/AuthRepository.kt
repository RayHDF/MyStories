package com.rayhdf.mystories.data.repository

import com.rayhdf.mystories.data.api.ApiService
import com.rayhdf.mystories.data.api.LoginResponse
import com.rayhdf.mystories.data.api.RegisterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepository(private val apiService: ApiService) {

    suspend fun register(name: String, email: String, password: String): RegisterResponse {
        return withContext(Dispatchers.IO) {
            apiService.register(name, email, password)
        }
    }

    suspend fun login(email: String, password: String): LoginResponse {
        return withContext(Dispatchers.IO) {
            apiService.login(email, password)
        }
    }
}