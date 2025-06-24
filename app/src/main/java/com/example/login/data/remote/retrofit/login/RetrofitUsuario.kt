package com.example.login.data.remote.retrofit.login

import com.example.login.data.remote.api.UserService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitUsuario {
    private var apiInstance: UserService? = null

    fun init(baseUrl: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiInstance = retrofit.create(UserService::class.java)
    }

    fun getApi(): UserService {
        return apiInstance ?: throw IllegalStateException("RetrofitClient not initialized")
    }
}

