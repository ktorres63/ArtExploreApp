package com.danp.artexploreapp.ui.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.danp.artexploreapp.auth.presentation.screens.login.LoginScreen
import com.danp.artexploreapp.auth.presentation.viewmodels.LoginViewModel
import com.danp.artexploreapp.ui.viewmodel.GalleryViewModel

@Composable
fun AppNavigation(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = Screens.ScreenHome.route) {
        composable(route = Screens.ScreenLogin.route) { LoginScreen(navController, LoginViewModel()) }
        composable(route = Screens.ScreenSignUp.route) { SignUp(navController) }
        composable(route = Screens.ScreenHome.route) { Home(navController) }

        composable(route = Screens.ScreenQrPainting.route) { QrScreen(navController) }
        composable(route = Screens.ScreenMap.route) { Map(navController) }
        composable(route = Screens.ScreenMapMuseum.route) { MapMuseum(navController) }

        composable(route = Screens.ScreenUser.route) { Perfil(navController) }

        composable(route = Screens.ScreenSettings.route) { Settings(navController) }
        composable(route = Screens.ScreenRoom1Map.route) {Room1(navController, GalleryViewModel(),) }



    }

}

