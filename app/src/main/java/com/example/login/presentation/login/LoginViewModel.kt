package com.example.login.presentation.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login.data.repository.UserRepository
import com.example.login.data.repository.CompanyRepository
import com.example.login.domain.model.user.ModelCompany
import com.example.login.domain.response.TokenResponse
import com.example.login.domain.response.CompaniaResponse
import com.example.login.domain.useCase.company.UserCaseCompany
import com.example.login.domain.useCase.token.UserCaseToken
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch


class LoginViewModel() : ViewModel() {

    //METODOS DE USUARIO
    private val userRepository = UserRepository()
    private val userCaseToken = UserCaseToken(userRepository)

    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> = _loginSuccess

    private val _tokenResponse = MutableLiveData<TokenResponse?>()
    val tokenResponse: LiveData<TokenResponse?> = _tokenResponse
    // METODOS DE COMAPAÑIA
    private val companyRepository = CompanyRepository()
    private val userCaseCompany = UserCaseCompany(companyRepository)

    private val _companyInfoList = MutableLiveData<List<CompaniaResponse>?>()
    val companyInfoList: LiveData<List<CompaniaResponse>?> = _companyInfoList


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

    fun getCompanyName(companyList: List<ModelCompany>?, token: String) {
        viewModelScope.launch {
            val companyInfoList = companyList?.map { company ->
                async {
                    try {
                        val result = userCaseCompany.getCompany(company.companyId, token)
                        val response = result.getOrNull()
                        if (response == null) {
                            Log.w("LoginViewModel", "No se obtuvo respuesta para companyId=${company.companyId}")
                        }
                        response
                    } catch (e: Exception) {
                        Log.e("LoginViewModel", "Error al obtener companyId=${company.companyId}", e)
                        null
                    }
                }
            }?.awaitAll()?.filterNotNull()
            _companyInfoList.value = companyInfoList
        }
    }
}


