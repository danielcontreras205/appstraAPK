package com.example.login.ui.LoginFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login.data.UserRepository
import com.example.login.dto.loginDtos.LoginResponse
import kotlinx.coroutines.launch


class LoginViewModel : ViewModel() {

    private val userRepository = UserRepository()

    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> = _loginSuccess

    private val _loginData = MutableLiveData<LoginResponse>()
    val loginData: LiveData<LoginResponse> = _loginData

    fun login(username: String, password: String) {
        viewModelScope.launch {
            val result = userRepository.authenticate(username, password)
            if (result.isSuccess) {
                _loginData.value = result.getOrNull()!!
                _loginSuccess.value = true
            } else {
                _loginSuccess.value = false
            }
        }
    }
}


