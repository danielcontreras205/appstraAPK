package com.example.login.data.remote.retrofit.company

import com.example.login.data.remote.api.CompanyService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitCompany {
    private var apiInstance: CompanyService? = null

    fun init(baseUrl: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiInstance = retrofit.create(CompanyService::class.java)
    }

    fun getApi(): CompanyService {
        return apiInstance ?: throw IllegalStateException("RetrofitClient not initialized")
    }
}