package com.example.login.data.repository

import com.example.login.data.remote.api.dto.loginDtos.LoginRequest
import com.example.login.data.remote.api.dto.loginDtos.LoginResponse
import com.example.login.data.remote.dto.UsuarioDTO.PersonResponse
import com.example.login.data.remote.retrofit.login.RetrofitUsuario

class UserRepository {

    suspend fun authenticate(username: String, password: String): Result<LoginResponse> {
        return try {
            val response = RetrofitUsuario.getApi().login(LoginRequest(username, password))
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

    suspend fun getPerson(personId: Int,token:String): Result<PersonResponse> {
        return try {
            val response = RetrofitUsuario.getApi().getPerson(personId,token)
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(Exception("Respuesta vacía"))
            } else {
                Result.failure(Exception("Error al obtener persona: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}

