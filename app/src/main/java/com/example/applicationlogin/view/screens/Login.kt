package com.example.applicationlogin.view.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.autoSaver
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.applicationlogin.model.Screens
import com.example.applicationlogin.ui.theme.ApplicationLoginTheme
import com.example.applicationlogin.view.components.AlternativeText
import com.example.applicationlogin.view.components.AppsAlternatives
import com.example.applicationlogin.view.components.ButtonPrimary
import com.example.applicationlogin.view.components.ButtonSecondary
import com.example.applicationlogin.view.components.DescriptionText
import com.example.applicationlogin.view.components.ForgotPasswrodText
import com.example.applicationlogin.view.components.Input
import com.example.applicationlogin.view.components.TitleText
import com.example.applicationlogin.viewmodel.LoginViewModel


@Preview(showBackground = true)
@Composable
private fun Preview() {
    ApplicationLoginTheme {
        Login()
    }
}

@Composable
fun Login(
    navController: NavHostController = rememberNavController(),
    myViewModelLogin: LoginViewModel = viewModel()
) {
    val focusManager = LocalFocusManager.current
    Scaffold(
        modifier = Modifier.clickable(
            onClick = {
                focusManager.clearFocus(true)} ,
            indication = null,
            interactionSource = remember { MutableInteractionSource() }

        )
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .background(Color.White)
                .padding(paddingValues)
                .padding(horizontal = 30.dp, vertical = 70.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TitleText("Login here")
                    Spacer(modifier = Modifier.height(30.dp))
                    DescriptionText("Welcome back you´ve been missed!")
                }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Email(myViewModelLogin)
                    Spacer(modifier = Modifier.height(20.dp))
                    Password(myViewModelLogin)
                    Spacer(modifier = Modifier.height(20.dp))
                    ForgotPasswrodText()
                    Spacer(modifier = Modifier.height(40.dp))
                    ButtonLogin(myViewModelLogin)
                    Spacer(modifier = Modifier.height(15.dp))
                    ButtonGoToRegister(navController)
                }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AlternativeText()
                    Spacer(modifier = Modifier.height(20.dp))
                    AppsAlternatives()
                }
            }
        }
    }
}

@Composable
private fun ButtonGoToRegister(navController: NavHostController) {
    ButtonSecondary("Create new account", fontSize = 16.sp) {

        if (navController.previousBackStackEntry?.destination?.route == Screens.Register.route) {
            navController.popBackStack(
                route = Screens.Register.route,
                inclusive = false,
                saveState = true
            )

        } else {
            navController.navigate(route = Screens.Register.route) {
                launchSingleTop = true
            }
        }

    }
}

@Composable
private fun ButtonLogin(myViewModelLogin: LoginViewModel) {
    ButtonPrimary("Sign in", enabled = myViewModelLogin.isCorrectTheInformation()) {
        Log.d("EVENT", "SIGN IN")
    }
}

@Composable
private fun Password(myViewModelLogin: LoginViewModel) {
    Input("Password", myViewModelLogin.password, KeyboardType.Password) { value ->
        myViewModelLogin.changeValues(myViewModelLogin.email, value)
    }
}

@Composable
private fun Email(myViewModelLogin: LoginViewModel) {
    Input("Email", myViewModelLogin.email, KeyboardType.Email) { value ->
        myViewModelLogin.changeValues(value, myViewModelLogin.password)
    }
}



