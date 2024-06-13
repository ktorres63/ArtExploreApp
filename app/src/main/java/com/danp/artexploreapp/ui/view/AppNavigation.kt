package com.danp.artexploreapp.ui.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavigation(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = Screens.ScreenLogin.route) {
        composable(route = Screens.ScreenLogin.route) { Login(navController) }
        composable(route = Screens.ScreenSignUp.route) { SignUp(navController) }
        composable(route = Screens.ScreenHome.route) { Home(navController) }

        composable(route = Screens.ScreenQrPainting.route) { QrScreen(navController) }
        composable(route = Screens.ScreenMap.route) { Map(navController) }
        composable(route = Screens.ScreenUser.route) { Perfil(navController) }





    }

}

