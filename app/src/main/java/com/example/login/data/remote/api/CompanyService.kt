package com.example.login.data.remote.api

import com.example.login.data.remote.dto.CompaniaDTO.CompanyResponse
import com.example.login.utils.constants.Company
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface CompanyService {
    @GET(Company.getCompany)
    suspend fun getCompany(@Path("companyId") companyId: Int, @Header("Authorization") token: String): Response<CompanyResponse>
}