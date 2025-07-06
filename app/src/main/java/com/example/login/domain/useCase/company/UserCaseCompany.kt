package com.example.login.domain.useCase.company

import com.example.login.data.repository.CompanyRepository
import com.example.login.domain.mapper.toDomainComaniaResponse
import com.example.login.domain.response.CompaniaResponse

class UserCaseCompany(private val companyRepository: CompanyRepository) {
    suspend fun getCompany(companyId: Int,token: String): Result<CompaniaResponse> {
        val result = companyRepository.getCompany(companyId,token)
        return result.map {it.toDomainComaniaResponse()}
    }
}