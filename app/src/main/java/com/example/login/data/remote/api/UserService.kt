package com.example.login.data.remote.api

import com.example.login.data.remote.dto.UsuarioDTO.LoginRequest
import com.example.login.data.remote.dto.UsuarioDTO.LoginResponse
import com.example.login.data.remote.dto.UsuarioDTO.PersonResponse
import com.example.login.utils.constants.Usuario
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface UserService {
    @POST(Usuario.urlToken)
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
    @GET(Usuario.getPersona)
    suspend fun getPerson(@Path("personId") personId: Int,@Header("Authorization") token: String): Response<PersonResponse>
}