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

data class ModelPerson(
    val personId: Int,
    val personFirstName: String,
    val personLastName: String,
    val personEmail: String,
    val personMobilePhone: String,
    val personLandlinePhone: String?,
    val personAddress: String,
    val city: ModelCity,
    val personRH: String?,
    val personBloodType: String,
    val personCreationDate: String,
    val personBirthdayDate: String?,
    val personChildren: String?,
    val personNumberIdentification: String?,
    val personDateIssueIdentification: String?,
    val personEditDate: String,
    val personEditUserID: Int,
    val user: ModelUser,
    val documentType: ModelDocumentType,
)
data class ModelCountry(
    val countryId: Int,
    val countryName: String,
    val countryCreationDate: String,
    val countryEditDate: String
)

data class ModelDepartment(
    val departmentId: Int,
    val departmentName: String,
    val departmentCreationDate: String,
    val departmentEditDate: String,
    val departmentEditUserID: Int,
    val country: ModelCountry
)

data class ModelProvince(
    val provinceId: Int,
    val provinceName: String,
    val provinceCreationDate: String,
    val provinceEditDate: String,
    val provinceEditUserID: Int,
    val department: ModelDepartment
)

data class ModelCity(
    val cityId: Int,
    val cityCode: String,
    val cityName: String,
    val cityCreationDate: String,
    val cityEditDate: String,
    val cityEditUserID: Int,
    val province: ModelProvince
)

data class ModelDocumentType(
    val documentTypeId: Int,
    val documentTypeName: String,
    val documentTypeInitial: String,
    val documentDescription: String,
    val documentTypeCreationDate: String,
    val documentTypeEditDate: String?,
    val documentTypeEditUserID: Int?
)

