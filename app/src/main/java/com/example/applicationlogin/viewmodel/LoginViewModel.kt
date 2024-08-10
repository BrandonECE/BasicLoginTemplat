package com.example.applicationlogin.viewmodel

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginViewModel  : ViewModel() {

    private var _email: String by mutableStateOf("")
    private var _password: String by mutableStateOf("")

    val email get() = _email
    val password get() = _password

    fun changeValues(email: String, password: String) {
        _email = email
        _password = password
    }

    private fun isCorrectTheEmail(): Boolean = Patterns.EMAIL_ADDRESS.matcher(_email).matches()
    private fun isCorrectThePassword(): Boolean = _password.length > 6

    fun isCorrectTheInformation(): Boolean = isCorrectTheEmail() && isCorrectThePassword()
}