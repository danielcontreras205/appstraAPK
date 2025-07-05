package com.example.login.domain.response

import com.example.login.domain.model.user.EntityCity
import com.example.login.domain.model.user.EntityDocumentType
import com.example.login.domain.model.user.EntityUser

data class PersonaResponse(
    val personId: Int,
    val personFirstName: String,
    val personLastName: String,
    val personEmail: String,
    val personMobilePhone: String?,
    val personLandlinePhone: String?,
    val personAddress: String,
    val city: EntityCity,
    val personRH: String?,
    val personBloodType: String,
    val personCreationDate: String,
    val personBirthdayDate: String?,
    val personChildren: String?,
    val personNumberIdentification: String?,
    val personDateIssueIdentification: String?,
    val personEditDate: String,
    val personEditUserID: Int,
    val user: EntityUser,
    val documentType: EntityDocumentType,
)