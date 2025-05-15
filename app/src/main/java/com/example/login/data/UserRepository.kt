package com.example.login.data

class UserRepository constructor() {
    fun authenticate(username: String, password: String): Boolean {
        // Simulación de login
        return username == "admin" && password == "1234"
    }
}
