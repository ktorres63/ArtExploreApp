package com.danp.artexploreapp.util.navigation

sealed class Screens(val route: String) {
    data object ScreenLogin: Screens("login")
    data object ScreenSignUp: Screens("signUp")
    data object ScreenMapMuseum : Screens("mapMuseum")

    data object ScreenSettings: Screens("settings")
    data object ScreenRoom1Map: Screens("room1Map")
    data object ScreenRoom1MapPaint3: Screens("room1MapPaint3")
    data object ScreenRoom1MapPaint1: Screens("room1MapPaint1")
    data object ScreenRoom1MapPaint2: Screens("room1MapPaint2")
    data object ScreenRoom1MapPaint4: Screens("room1MapPaint4")
    data object ScreenRoom1MapPaint5: Screens("room1MapPaint5")


    //Bottom Bar
    data object ScreenHome : Screens(route = "home")
    data object ScreenMap : Screens(route = "map")
    data object ScreenUser : Screens(route = "user")
    data object ScreenQrPainting: Screens("qr")


}
