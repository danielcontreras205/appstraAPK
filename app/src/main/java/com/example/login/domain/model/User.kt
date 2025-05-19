package com.example.login.domain.model

data class User(
    val id: Int,
    val username: String,
    val roleId: Int,
    val stateName: String,
    val companies: List<Company>
)

data class Company(
    val companyId: Int,
    val employeeRoleId: Int,
    val employeeId: Int?,
    val personId: Int
)
