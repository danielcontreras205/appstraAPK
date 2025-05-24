package com.example.login.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login.data.repository.UserRepository
import com.example.login.domain.response.TokenResponse
import com.example.login.domain.useCase.token.UserCaseToken
import kotlinx.coroutines.launch


class LoginViewModel() : ViewModel() {

    private val userRepository = UserRepository()
    private val userCaseToken = UserCaseToken(userRepository)

    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> = _loginSuccess

    private val _tokenResponse = MutableLiveData<TokenResponse?>()
    val tokenResponse: LiveData<TokenResponse?> = _tokenResponse

    fun login(username: String, password: String) {
        viewModelScope.launch {
            val result = userCaseToken.execute(username, password)
            if (result.isSuccess) {
                val loginResponse = result.getOrNull()
                _tokenResponse.value = loginResponse
                _loginSuccess.value = true
            } else {
                _loginSuccess.value = false
                _tokenResponse.value = null
            }
        }
    }
}


