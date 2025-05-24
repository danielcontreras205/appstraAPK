package com.example.login.domain.useCase.token

import com.example.login.data.repository.UserRepository
import com.example.login.domain.mapper.toDomainTokenResponse
import com.example.login.domain.response.TokenResponse

class UserCaseToken(private val userRepository: UserRepository) {

    suspend fun execute(username: String, password: String): Result<TokenResponse> {
        val result = userRepository.authenticate(username, password)
        return result.map { it.toDomainTokenResponse() }
    }
}