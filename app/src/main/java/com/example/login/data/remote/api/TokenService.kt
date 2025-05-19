package com.example.login.data.remote.api

import com.example.login.utils.constants.Token
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import com.example.login.data.remote.api.dto.loginDtos.LoginRequest
import com.example.login.data.remote.api.dto.loginDtos.LoginResponse

interface TokenService {
    @POST(Token.urlToken)
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
}