package com.example.login.domain.response

data class CompaniaResponse (
    val companyId: Int,
    val companyName: String?,
    val companyNit: String?,
    val companyAddress: String?,
    val cityId: Int?,
    val companyWebsite: String?,
    val companyCreationDate: String?,
    val companyEditionDate: String?,
    val companyEditUserID: Int?
)