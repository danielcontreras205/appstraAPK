package com.example.login.data.remote.retrofit.login

import com.example.login.utils.constants.GeneralPaths
import com.example.login.data.remote.api.TokenService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var apiInstance: TokenService? = null

    fun init(baseUrl: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiInstance = retrofit.create(TokenService::class.java)
    }

    fun getApi(): TokenService {
        return apiInstance ?: throw IllegalStateException("RetrofitClient not initialized")
    }
}

