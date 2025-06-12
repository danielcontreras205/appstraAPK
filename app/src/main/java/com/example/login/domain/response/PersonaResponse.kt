package com.example.login.domain.response

import com.example.login.domain.model.user.ModelCity
import com.example.login.domain.model.user.ModelDocumentType
import com.example.login.domain.model.user.ModelUser

data class PersonaResponse(
    val personId: Int,
    val personFirstName: String,
    val personLastName: String,
    val personEmail: String,
    val personMobilePhone: String?,
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