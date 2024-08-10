package com.example.applicationlogin.view.screens

import android.util.Log
import androidx.collection.forEach
import androidx.collection.valueIterator
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.get
import com.example.applicationlogin.model.Screens
import com.example.applicationlogin.ui.theme.ApplicationLoginTheme
import com.example.applicationlogin.view.components.AlternativeText
import com.example.applicationlogin.view.components.AppsAlternatives
import com.example.applicationlogin.view.components.ButtonPrimary
import com.example.applicationlogin.view.components.ButtonSecondary
import com.example.applicationlogin.view.components.DescriptionText
import com.example.applicationlogin.view.components.Input
import com.example.applicationlogin.view.components.TitleText
import com.example.applicationlogin.viewmodel.RegisterViewModel

@Preview(showBackground = true)
@Composable
private fun Preview() {
    ApplicationLoginTheme {
        Register()
    }
}

@Composable
fun Register(navController: NavHostController = rememberNavController(),
             myViewModelRegister: RegisterViewModel = viewModel()) {
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
                    TitleText("Create Account")
                    Spacer(modifier = Modifier.height(15.dp))
                    DescriptionText("Create an account so you " +
                            "can explore al the existing jobs", 14.sp, 360.dp)
                }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Email(myViewModelRegister)
                    Spacer(modifier = Modifier.height(20.dp))
                    Password1(myViewModelRegister)
                    Spacer(modifier = Modifier.height(20.dp))
                    Password2(myViewModelRegister)
                    Spacer(modifier = Modifier.height(45.dp))
                    ButtonRegister(myViewModelRegister)
                    Spacer(modifier = Modifier.height(15.dp))
                    ButtonGoToLogin(navController)
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
private fun ButtonGoToLogin(navController: NavHostController) {
    ButtonSecondary("Already have an account", fontSize = 16.sp) {

        if (navController.previousBackStackEntry?.destination?.route == Screens.Login.route) {
            navController.popBackStack(
                route = Screens.Login.route,
                inclusive = false,
                saveState = true
            )
        } else {
            navController.navigate(route = Screens.Login.route) {
                launchSingleTop = true
            }
        }

    }
}

@Composable
private fun ButtonRegister(myViewModelRegister: RegisterViewModel) {
    ButtonPrimary("Sign up", enabled = myViewModelRegister.isCorrectTheInformation()) {
        Log.d("EVENT", "SIGN UP")
    }
}

@Composable
private fun Password2(myViewModelRegister: RegisterViewModel) {
    Input("Confirm password", myViewModelRegister.password2, KeyboardType.Password) { value ->
        myViewModelRegister.changeValues(
            myViewModelRegister.email,
            myViewModelRegister.password1,
            value
        )
    }
}

@Composable
private fun Password1(myViewModelRegister: RegisterViewModel) {
    Input("Password", myViewModelRegister.password1, KeyboardType.Password) { value ->
        myViewModelRegister.changeValues(
            myViewModelRegister.email,
            value,
            myViewModelRegister.password2
        )

    }
}

@Composable
private fun Email(myViewModelRegister: RegisterViewModel) {
    Input("Email", myViewModelRegister.email, KeyboardType.Email) { value ->
        myViewModelRegister.changeValues(
            value,
            myViewModelRegister.password1,
            myViewModelRegister.password2
        )
    }
}


