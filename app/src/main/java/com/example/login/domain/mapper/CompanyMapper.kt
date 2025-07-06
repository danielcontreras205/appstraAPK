package com.example.login.domain.mapper

import com.example.login.data.remote.dto.CompaniaDTO.CompanyResponse
import com.example.login.domain.model.company.EntityComapany
import com.example.login.domain.response.CompaniaResponse

fun CompanyResponse.toDomainComaniaResponse(): CompaniaResponse{
    return CompaniaResponse(
        companyId = this.companyId,
        companyName = this.companyName,
        companyNit = this.companyNit,
        companyAddress = this.companyAddress,
        cityId = this.cityId,
        companyWebsite = this.companyWebsite,
        companyCreationDate = this.companyCreationDate,
        companyEditionDate = this.companyEditionDate,
        companyEditUserID = this.companyEditUserID
    )
}
fun CompaniaResponse.toEntityCompany(): EntityComapany? {
    return if (
        companyName != null &&
        companyNit != null &&
        companyAddress != null &&
        cityId != null &&
        companyWebsite != null &&
        companyCreationDate != null &&
        companyEditionDate != null &&
        companyEditUserID != null
    ) {
        EntityComapany(
            companyId = companyId,
            companyName = companyName,
            companyNit = companyNit,
            companyAddress = companyAddress,
            cityId = cityId,
            companyWebsite = companyWebsite,
            companyCreationDate = companyCreationDate,
            companyEditionDate = companyEditionDate,
            companyEditUserID = companyEditUserID
        )
    } else {
        null // alguno de los campos requeridos es null
    }
}
