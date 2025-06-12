package com.example.login.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login.data.repository.UserRepository
import com.example.login.domain.response.PersonaResponse
import com.example.login.domain.useCase.token.UserCaseToken
import kotlinx.coroutines.launch

class HomeViewModel(): ViewModel() {

    private val userRepository = UserRepository()
    private val userCaseGetPerson = UserCaseToken(userRepository)

    private val _personResponse = MutableLiveData<PersonaResponse?>()
    val personResponse: LiveData<PersonaResponse?> = _personResponse

    fun getPerson(personId: Int,token: String) {
        viewModelScope.launch {
            val result = userCaseGetPerson.gatPerson(personId,token)
            if (result.isSuccess) {
                val personResponse = result.getOrNull()
                _personResponse.value = personResponse
            } else {
                _personResponse.value = null
            }
        }
    }
}