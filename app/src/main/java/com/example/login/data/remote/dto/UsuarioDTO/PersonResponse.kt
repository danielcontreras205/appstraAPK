package com.example.login.data.remote.dto.UsuarioDTO

data class PersonResponse(
    val personId: Int,
    val personFirstName: String,
    val personLastName: String,
    val personEmail: String,
    val personMobilePhone: String,
    val personLandlinePhone: String?,
    val personAddress: String,
    val city: City,
    val personRH: String?,
    val personBloodType: String,
    val personCreationDate: String,
    val personBirthdayDate: String?,
    val personChildren: String?,
    val personNumberIdentification: String?,
    val personDateIssueIdentification: String?,
    val personEditDate: String,
    val personEditUserID: Int,
    val user: User,
    val documentType: DocumentType,
)

data class Country(
    val countryId: Int,
    val countryName: String,
    val countryCreationDate: String,
    val countryEditDate: String
)

data class Department(
    val departmentId: Int,
    val departmentName: String,
    val departmentCreationDate: String,
    val departmentEditDate: String,
    val departmentEditUserID: Int,
    val country: Country
)

data class Province(
    val provinceId: Int,
    val provinceName: String,
    val provinceCreationDate: String,
    val provinceEditDate: String,
    val provinceEditUserID: Int,
    val department: Department
)

data class City(
    val cityId: Int,
    val cityCode: String,
    val cityName: String,
    val cityCreationDate: String,
    val cityEditDate: String,
    val cityEditUserID: Int,
    val province: Province
)

data class DocumentType(
    val documentTypeId: Int,
    val documentTypeName: String,
    val documentTypeInitial: String,
    val documentDescription: String,
    val documentTypeCreationDate: String,
    val documentTypeEditDate: String?,
    val documentTypeEditUserID: Int?
)
