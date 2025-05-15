package com.example.login.ui.LoginFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.login.data.UserRepository


class LoginViewModel : ViewModel() {

    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> = _loginSuccess

    private val userRepository = UserRepository() // instanciación manual

    fun login(username: String, password: String) {
        _loginSuccess.value = userRepository.authenticate(username, password)
    }
}

