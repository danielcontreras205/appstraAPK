package com.example.login.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login.data.repository.UserRepository
import com.example.login.data.remote.api.dto.loginDtos.LoginResponse
import com.example.login.domain.mapper.toDomainUser
import com.example.login.domain.model.User
import kotlinx.coroutines.launch


class LoginViewModel : ViewModel() {

    private val userRepository = UserRepository()

    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> = _loginSuccess

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    fun login(username: String, password: String) {
        viewModelScope.launch {
            val result = userRepository.authenticate(username, password)
            if (result.isSuccess) {
                val loginResponse = result.getOrNull()
                _user.value = loginResponse?.toDomainUser()
                _loginSuccess.value = true
            } else {
                _loginSuccess.value = false
            }
        }
    }
}


