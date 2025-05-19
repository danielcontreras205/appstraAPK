package com.example.login.data.remote.retrofit.login

import com.example.login.utils.constants.GeneralPaths
import com.example.login.data.remote.api.TokenService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl(GeneralPaths.PRODUCCION)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: TokenService = retrofit.create(TokenService::class.java)
}
