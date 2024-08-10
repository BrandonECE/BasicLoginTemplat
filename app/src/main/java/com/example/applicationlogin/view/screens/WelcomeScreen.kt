package com.example.applicationlogin.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.applicationlogin.R
import com.example.applicationlogin.model.Screens
import com.example.applicationlogin.ui.theme.ApplicationLoginTheme
import com.example.applicationlogin.view.components.ButtonPrimaryRowScope
import com.example.applicationlogin.view.components.ButtonSecondaryRowScope
import com.example.applicationlogin.view.components.DescriptionText
import com.example.applicationlogin.view.components.TitleText

@Preview(showBackground = true)
@Composable
private fun Preview() {
    ApplicationLoginTheme {
        WelcomeScreen()
    }
}

@Composable
fun WelcomeScreen(navController: NavHostController = rememberNavController()){
    Scaffold {paddingValues ->
        Box(
            modifier = Modifier
                .background(Color.White)
                .padding(paddingValues)
                .padding(bottom = 70.dp),
            contentAlignment = Alignment.Center
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ){

                Image(
                    painter = painterResource(id = R.drawable.ic_welcome),
                    contentDescription = "R.drawable.ic_welcome"
                )

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Title()
                        Spacer(modifier = Modifier.height(25.dp))
                        DescriptionText(
                            "Explore all the existing job roles based " +
                                    "on your interest and study major",
                            15.sp,
                            300.dp,
                            FontWeight.Bold
                        )
                    }

                Box(modifier = Modifier.padding(horizontal = 40.dp)){
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        ButtonPrimaryRowScope(text = "Login"){
                            navController.navigate(route = Screens.Login.route)
                        }
                        Spacer(modifier = Modifier.width(30.dp))
                        ButtonSecondaryRowScope(text = "Register", textcolor = Color.Black){
                            navController.navigate(route = Screens.Register.route)
                        }
                    }
                }


            }

        }
    }
}

@Composable
private fun Title() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        TitleText("Discover Your", 34.sp, FontWeight.Bold)
        Spacer(modifier = Modifier.height(6.dp))
        TitleText("Dream Job here", 34.sp, FontWeight.Bold)
    }
}





