package com.example.login.retrofit.login

import com.example.login.constants.paths.GeneralPaths
import com.example.login.service.TokenService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl(GeneralPaths.PRODUCCION)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: TokenService = retrofit.create(TokenService::class.java)
}
