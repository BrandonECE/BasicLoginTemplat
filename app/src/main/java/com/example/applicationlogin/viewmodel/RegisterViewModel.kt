package com.example.applicationlogin.viewmodel

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel() {

    private var _email: String by mutableStateOf("")
    private var _password1: String by mutableStateOf("")
    private var _password2: String by mutableStateOf("")

    val email get() = _email
    val password1 get() = _password1
    val password2 get() = _password2

    fun changeValues(email: String, password1: String, password2: String){
        _email = email
        _password1 = password1
        _password2 = password2
    }

    private fun isCorrectTheEmail(): Boolean = Patterns.EMAIL_ADDRESS.matcher(_email).matches()
    private fun isCorrectThePasswords(): Boolean = _password1.length > 6 && _password2.length > 6 && _password1 == _password2

    fun isCorrectTheInformation(): Boolean = isCorrectTheEmail() && isCorrectThePasswords()

}