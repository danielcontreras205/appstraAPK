package com.example.login.data.repository

import com.example.login.data.remote.dto.CompaniaDTO.CompanyResponse
import com.example.login.data.remote.retrofit.company.RetrofitCompany


class CompanyRepository {
    suspend fun getCompany(companyId: Int,token:String): Result<CompanyResponse> {
        return try {
            val response = RetrofitCompany.getApi().getCompany(companyId,token)
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(Exception("Respuesta vacía"))
            } else {
                Result.failure(Exception("Error al obtener la compañia: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}