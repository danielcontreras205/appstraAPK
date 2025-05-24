package com.example.login.domain.model.user

data class ModelUser(
    val userId: Int,
    val userUser: String,
    val roleId: Int,
    val state: ModelState,
    val userCreationDate: String,
    val userEditDate: String,
    val userEditUserID: Int
)

data class ModelCompany(
    val companyId: Int,
    val employeeRoleId: Int,
    val employeeId: Int?,
    val personId: Int
)

data class ModelState(
    val stateId: Int,
    val stateName: String,
    val stateDescription: String,
    val stateCreationDate: String,
    val stateEditDate: String,
    val stateEditUserID: Int,
    val stateType: ModelStateType
)

data class ModelStateType(
    val stateTypeId: Int,
    val stateTypeName: String,
    val stateTypeDescription: String,
    val stateTypeCreationDate: String,
    val stateTypeEditDate: String,
    val stateTypeEditUserID: Int
)
