package com.example.applicationlogin.model

private object Routes{
    const val WELCOMESCREEN = "welcomeScreen"
    const val LOGIN = "login"
    const val REGISTER = "register"
}

sealed class Screens(val route: String){
    data object WelcomeScreen: Screens(Routes.WELCOMESCREEN)
    data object Login: Screens(Routes.LOGIN)
    data object Register: Screens(Routes.REGISTER)
}
