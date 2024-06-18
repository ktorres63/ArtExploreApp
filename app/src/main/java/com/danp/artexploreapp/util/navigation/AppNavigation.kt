package com.danp.artexploreapp.util.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.danp.artexploreapp.auth.presentation.screens.login.LoginScreen
import com.danp.artexploreapp.auth.presentation.viewModels.LoginViewModel
import com.danp.artexploreapp.home.presentation.screens.Home
import com.danp.artexploreapp.googleMap.presentation.screens.Map
import com.danp.artexploreapp.GalleryArt.presentation.screens.MapMuseum
import com.danp.artexploreapp.profile.presentation.Perfil
import com.danp.artexploreapp.qr.presentation.QrScreen
import com.danp.artexploreapp.artRoom.presentation.screens.Room1
import com.danp.artexploreapp.settings.presentation.screens.Settings
import com.danp.artexploreapp.auth.presentation.screens.register.SignUp
import com.danp.artexploreapp.artRoom.presentation.viewModels.ArtRoomViewModel

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
        composable(route = Screens.ScreenRoom1Map.route) { Room1(navController, ArtRoomViewModel(),) }



    }

}
