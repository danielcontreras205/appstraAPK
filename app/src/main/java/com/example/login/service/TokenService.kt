package com.example.login.service

import com.example.login.constants.paths.Token
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import com.example.login.dto.loginDtos.LoginRequest
import com.example.login.dto.loginDtos.LoginResponse

interface TokenService {
    @POST(Token.urlToken)
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
}