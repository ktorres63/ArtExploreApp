package com.danp.artexploreapp.ui.view

import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.danp.artexploreapp.ui.theme.BoneJC

@Composable
fun BottomBar(
    navController: NavController,
    state: Boolean,
    modifier: Modifier = Modifier
) {
    /*
    val screens = listOf(
        BottomNavigationItems.ScreenHome,
        BottomNavigationItems.ScreenQrPainting,
        BottomNavigationItems.ScreenMap,
        BottomNavigationItems.ScreenUser
    )
    */


    NavigationBar(
        modifier = modifier,
        containerColor = BoneJC
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

    }

}

