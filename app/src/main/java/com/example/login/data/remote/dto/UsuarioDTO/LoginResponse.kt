package com.example.login.data.remote.dto.UsuarioDTO

class LoginResponse(
    val token: String,
    val user: User,
    val message: String,
    val listCompany: List<Company>
)

data class User(
    val userId: Int,
    val userUser: String,
    val roleId: Int,
    val state: State,
    val userCreationDate: String,
    val userEditDate: String,
    val userEditUserID: Int
)

data class State(
    val stateId: Int,
    val stateName: String,
    val stateDescription: String,
    val stateCreationDate: String,
    val stateEditDate: String,
    val stateEditUserID: Int,
    val stateType: StateType
)

data class StateType(
    val stateTypeId: Int,
    val stateTypeName: String,
    val stateTypeDescription: String,
    val stateTypeCreationDate: String,
    val stateTypeEditDate: String,
    val stateTypeEditUserID: Int
)

data class Company(
    val companyId: Int,
    val employeeRoleId: Int?,
    val employeeId: Int?,
    val personId: Int?
)
