package com.danp.artexploreapp.ui.view

sealed class Screens(val route: String) {
    data object ScreenLogin: Screens("login")
    data object ScreenSignUp: Screens("signUp")
    data object ScreenHome: Screens("home")
    data object ScreenMap: Screens("map")
    data object ScreenQrPainting: Screens("qr")
    data object ScreenUser: Screens("user")
    data object ScreenGaleryMap: Screens("galeryMap")

}