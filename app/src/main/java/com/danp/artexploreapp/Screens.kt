package com.danp.artexploreapp

sealed class Screens(val screen: String) {
    data object Home:Screens("home")
    data object Map:Screens("map")
    data object Perfil:Screens("perfil")

}