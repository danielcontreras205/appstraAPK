package com.example.login.domain.response

import com.example.login.domain.model.user.ModelCompany
import com.example.login.domain.model.user.EntityUser


data class TokenResponse(
    val token: String,
    val user: EntityUser,
    val messageDTO: String,
    val listCompany: List<ModelCompany>
)