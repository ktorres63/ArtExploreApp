package com.danp.artexploreapp.ui.view

sealed class Screens(val route: String) {
    data object ScreenLogin: Screens("login")
    data object ScreenSignUp: Screens("signUp")
    data object ScreenMapMuseum : Screens("mapMuseum")

    data object ScreenSettings: Screens("settings")
    data object ScreenRoom1Map: Screens("room1Map")


    //Bottom Bar
    data object ScreenHome :Screens(route = "home")
    data object ScreenMap : Screens(route = "map")
    data object ScreenUser : Screens(route = "user")
    data object ScreenQrPainting: Screens("qr")


}
