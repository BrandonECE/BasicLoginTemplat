package com.example.applicationlogin.view

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.applicationlogin.model.Screens
import com.example.applicationlogin.ui.theme.ApplicationLoginTheme
import com.example.applicationlogin.view.screens.Login
import com.example.applicationlogin.view.screens.Register
import com.example.applicationlogin.view.screens.WelcomeScreen
import kotlin.math.log

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun App() {
    val navController = rememberNavController()

    val currentBackStackEntry = navController.currentBackStackEntryAsState()
    var popRoute: String? by remember { mutableStateOf(null) }

    LaunchedEffect(currentBackStackEntry.value?.destination?.route) {
        val currentRoute = navController.currentDestination?.route
        val previousBackStackEntry = navController.previousBackStackEntry
        if (previousBackStackEntry != null) {
            val previousDestinationRoute = previousBackStackEntry.destination.route
            if (popRoute != previousDestinationRoute) {
                Log.d("PRUEBAAA", "POP DE: $popRoute ")
            }
            popRoute = currentRoute
        } else {
            Log.d("PRUEBAAA", "POP DE: $popRoute ")
            popRoute = currentRoute
        }
    }

    ApplicationLoginTheme {
        NavHost(navController = navController, startDestination = Screens.WelcomeScreen.route) {
            welcomeScreen(navController)
            login(navController, popRoute)
            register(navController, popRoute)
        }
    }
}

private fun NavGraphBuilder.welcomeScreen(navController: NavHostController) {
    composable(route = Screens.WelcomeScreen.route,
        exitTransition = {fadeOut(tween(durationMillis = 0))},
        popEnterTransition = {fadeIn(tween(durationMillis = 0))}) {
        WelcomeScreen(navController)
    }
}




private fun NavGraphBuilder.login(navController: NavHostController, popRoute: String?) {
    composable(route = Screens.Login.route,
        enterTransition = {
            when(navController.previousBackStackEntry?.destination?.route){
                Screens.WelcomeScreen.route -> slideInVertically{it}
                Screens.Register.route -> slideInHorizontally { -it }
                else -> null
            }
        },
        exitTransition = {
            Log.d("PRUEBAAA1", "LOGIN (EXITANIM): ${navController.currentDestination?.route}")
            when(navController.currentDestination?.route){
                Screens.Register.route -> slideOutHorizontally { -it }
                else -> null
            }
        },
        popExitTransition = {
            Log.d("PRUEBAA2", "COMPOSABLE: LOGIN (POP-EXIT)")
            Log.d("PRUEBAA2", "RUTA: ${navController.currentDestination?.route}")
            when(navController.currentDestination?.route){
                Screens.WelcomeScreen.route -> slideOutVertically{it}
                Screens.Register.route -> slideOutHorizontally { -it }
                else -> null
            }
        },
        popEnterTransition = {
            Log.d("PRUEBAA2", "COMPOSABLE: LOGIN (POP-ENTER)")
            Log.d("PRUEBAA2", "RUTA: ${navController.currentDestination?.route}")
            when(popRoute){
                Screens.WelcomeScreen.route -> {fadeIn(tween(durationMillis = 100))}
                Screens.Register.route -> slideInHorizontally { -it }
                else -> null
            }
        }

        ) {
        Login(navController)
    }
}

private fun NavGraphBuilder.register(navController: NavHostController, popRoute: String?) {
    composable(route = Screens.Register.route,
        enterTransition = {
            when(navController.previousBackStackEntry?.destination?.route){
                Screens.WelcomeScreen.route -> slideInVertically{it}
                Screens.Login.route -> slideInHorizontally { it }
                else -> null
            }
        },
        exitTransition = {
            Log.d("PRUEBAAA1", "REGISTER (EXITANIM): ${navController.currentDestination?.route}")
            when(navController.currentDestination?.route){
                Screens.Login.route -> slideOutHorizontally { it }
                else -> null
            }
        },
        popExitTransition = {
            Log.d("PRUEBAA1", "COMPOSABLE: REGISTER (POP-EXIT)")
            Log.d("PRUEBAA1", "RUTA: ${navController.currentDestination?.route}")
            when(navController.currentDestination?.route){
                Screens.WelcomeScreen.route -> slideOutVertically{it}
                Screens.Login.route -> slideOutHorizontally { it }
                else -> null
            }
        },
        popEnterTransition = {
            Log.d("PRUEBAA1", "COMPOSABLE: REGISTER (POP-ENTER)")
            Log.d("PRUEBAA1", "RUTA: ${navController.currentDestination?.route}")
            when(popRoute){
                Screens.WelcomeScreen.route -> {fadeIn(tween(durationMillis = 100))}
                Screens.Login.route -> slideInHorizontally { it }
                else -> null
            }
        }
    ) {
        Register(navController)
    }
}


//private fun NavGraphBuilder.login(navController: NavHostController, popRoute: String?) {
//    composable(route = Screens.Login.route,
//        enterTransition = {
//            when(navController.previousBackStackEntry?.destination?.route){
//                Screens.WelcomeScreen.route -> slideInVertically{it}
//                Screens.Register.route -> slideInHorizontally { it }
//                else -> null
//            }
//        },
//        exitTransition = {
//            Log.d("PRUEBAAA1", "LOGIN (EXITANIM): ${navController.currentDestination?.route}")
//            when(navController.currentDestination?.route){
//                Screens.Register.route -> slideOutHorizontally { -it }
//                else -> null
//            }
//        },
//        popExitTransition = {
//            Log.d("PRUEBAA2", "COMPOSABLE: LOGIN (POP-EXIT)")
//            Log.d("PRUEBAA2", "RUTA: ${navController.currentDestination?.route}")
//            when(navController.currentDestination?.route){
//                Screens.WelcomeScreen.route -> slideOutVertically{it}
//                Screens.Register.route -> slideOutHorizontally { it }
//                else -> null
//            }
//        },
//        popEnterTransition = {
//            Log.d("PRUEBAA2", "COMPOSABLE: LOGIN (POP-ENTER)")
//            Log.d("PRUEBAA2", "RUTA: ${navController.currentDestination?.route}")
//            when(popRoute){
//                Screens.WelcomeScreen.route -> {fadeIn(tween(durationMillis = 100))}
//                Screens.Register.route -> slideInHorizontally { -it }
//                else -> null
//            }
//        }
//
//    ) {
//        Login(navController)
//    }
//}
//
//private fun NavGraphBuilder.register(navController: NavHostController, popRoute: String?) {
//    composable(route = Screens.Register.route,
//        enterTransition = {
//            when(navController.previousBackStackEntry?.destination?.route){
//                Screens.WelcomeScreen.route -> slideInVertically{it}
//                Screens.Login.route -> slideInHorizontally { it }
//                else -> null
//            }
//        },
//        exitTransition = {
//            Log.d("PRUEBAAA1", "REGISTER (EXITANIM): ${navController.currentDestination?.route}")
//            when(navController.currentDestination?.route){
//                Screens.Login.route -> slideOutHorizontally {- it }
//                else -> null
//            }
//        },
//        popExitTransition = {
//            Log.d("PRUEBAA1", "COMPOSABLE: REGISTER (POP-EXIT)")
//            Log.d("PRUEBAA1", "RUTA: ${navController.currentDestination?.route}")
//            when(navController.currentDestination?.route){
//                Screens.WelcomeScreen.route -> slideOutVertically{it}
//                Screens.Login.route -> slideOutHorizontally { it }
//                else -> null
//            }
//        },
//        popEnterTransition = {
//            Log.d("PRUEBAA1", "COMPOSABLE: REGISTER (POP-ENTER)")
//            Log.d("PRUEBAA1", "RUTA: ${navController.currentDestination?.route}")
//            when(popRoute){
//                Screens.WelcomeScreen.route -> {fadeIn(tween(durationMillis = 100))}
//                Screens.Login.route -> slideInHorizontally { -it }
//                else -> null
//            }
//        }
//    ) {
//        Register(navController)
//    }
//}



