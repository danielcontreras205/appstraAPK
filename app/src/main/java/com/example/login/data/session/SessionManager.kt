package com.example.login.data.session

import android.content.Context
import com.example.login.domain.model.user.EntityUser
import com.example.login.domain.model.user.ModelCompany
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SessionManager(context: Context){

    companion object {
        private const val PREF_NAME = "UserSession"
        private const val KEY_TOKEN = "token"
        private const val KEY_USER = "user"
        private const val KEY_COMPANY_LIST = "listCompany"
        private const val KEY_COMPANY = "company"
        private const val KEY_MESSAGE = "message"
        private const val KEY_AMBIENTE  = "ambiente"
    }

    private val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()
    private val gson = Gson()

    fun setAmbiente( ambiente: String){
        val editor =  sharedPreferences.edit()
        editor.putString(KEY_AMBIENTE, ambiente);
        editor.apply()
    }

    fun getAmbiente(): String? {
        return sharedPreferences.getString(KEY_AMBIENTE, null)
    }


    fun saveSession(token: String, user: EntityUser, companyList: List<ModelCompany>, message: String, company: ModelCompany?) {
        editor.putString(KEY_TOKEN, token)
        editor.putString(KEY_USER, gson.toJson(user))
        editor.putString(KEY_COMPANY_LIST, gson.toJson(companyList))
        editor.putString(KEY_MESSAGE, message)
        editor.putString(KEY_COMPANY, gson.toJson(company))
        editor.apply()
    }

    fun getToken(): String? = sharedPreferences.getString(KEY_TOKEN, null)

    fun getUser(): EntityUser? {
        val json = sharedPreferences.getString(KEY_USER, null)
        return json?.let { gson.fromJson(it, EntityUser::class.java) }
    }

    fun getCompanyList(): List<ModelCompany>? {
        val json = sharedPreferences.getString(KEY_COMPANY_LIST, null)
        val type = object : TypeToken<List<ModelCompany>>() {}.type
        return json?.let { gson.fromJson(it, type) }
    }

    fun getCompanyListById(companyId: Int): ModelCompany? {
        return getCompanyList()?.firstOrNull { it.companyId == companyId }
    }

    fun getMessage(): String? = sharedPreferences.getString(KEY_MESSAGE, null)

    fun clearSession() {
        editor.clear()
        editor.apply()
    }

    fun setSelectedCompany(company: ModelCompany) {
        val json = gson.toJson(company)
        sharedPreferences.edit().putString(KEY_COMPANY, json).apply()
    }

    fun getSelectedCompany(): ModelCompany? {
        val json = sharedPreferences.getString(KEY_COMPANY, null)
        return json?.let { gson.fromJson(it, ModelCompany::class.java) }
    }


}