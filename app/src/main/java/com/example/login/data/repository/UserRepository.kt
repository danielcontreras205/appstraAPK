package com.example.login.data.repository

import com.example.login.data.remote.api.dto.loginDtos.LoginRequest
import com.example.login.data.remote.api.dto.loginDtos.LoginResponse
import com.example.login.data.remote.retrofit.login.RetrofitClient

class UserRepository {

    suspend fun authenticate(username: String, password: String): Result<LoginResponse> {
        return try {
            val response = RetrofitClient.api.login(LoginRequest(username, password))
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(Exception("Respuesta vacía"))
            } else {
                Result.failure(Exception("Error en login: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

