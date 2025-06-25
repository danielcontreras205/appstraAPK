package com.example.login.presentation.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Trabajando en la nueva ventana... proximamente"
    }
    val text: LiveData<String> = _text
}