package com.danp.artexploreapp.util

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.danp.artexploreapp.R
import com.danp.artexploreapp.ui.theme.PrimaryColor
import com.danp.artexploreapp.ui.theme.SecondaryColor
import com.danp.artexploreapp.util.navigation.AppNavigation
import com.danp.artexploreapp.util.navigation.Screens

val bottomBarRoutes = setOf(
    Screens.ScreenHome.route,
    Screens.ScreenQrPainting.route,
    Screens.ScreenMap.route,
    Screens.ScreenUser.route
)

@Composable
fun MainScreen(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val isSelected = currentRoute

    Scaffold(
        bottomBar = {
            Log.i("ROUTE", "$currentRoute")
            if (currentRoute in bottomBarRoutes) {
                NavigationBar(containerColor = PrimaryColor) {
                    NavigationBarItem(
                        colors = NavigationBarItemDefaults.colors(indicatorColor = SecondaryColor),
                        selected = (currentRoute == Screens.ScreenHome.route),
                        onClick = {
                            if (currentRoute != Screens.ScreenHome.route) {
                                navController.navigate(Screens.ScreenHome.route) {
                                    popUpTo(navController.graph.startDestinationId)
                                    launchSingleTop = true

                                }
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Home,
                                contentDescription = null,
                            )


                        })
                    NavigationBarItem(
                        colors = NavigationBarItemDefaults.colors(indicatorColor = SecondaryColor),
                        selected = (currentRoute == Screens.ScreenQrPainting.route),
                        onClick = {
                            if (currentRoute != Screens.ScreenQrPainting.route) {
                                navController.navigate(Screens.ScreenQrPainting.route) {
                                    popUpTo(navController.graph.startDestinationId)
                                    launchSingleTop = true

                                }
                            }
                        },
                        icon = {
                            Icon(
                                painterResource(id = R.drawable.ic_qr),
                                contentDescription = null,
                            )
                        })
                    NavigationBarItem(
                        colors = NavigationBarItemDefaults.colors(indicatorColor = SecondaryColor),
                        selected = (currentRoute == Screens.ScreenMap.route),
                        onClick = {
                            if (currentRoute != Screens.ScreenMap.route) {
                                navController.navigate(Screens.ScreenMap.route) {
                                    popUpTo(navController.graph.startDestinationId)
                                    launchSingleTop = true

                                }
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = null,

                                )
                        })
                    NavigationBarItem(
                        colors = NavigationBarItemDefaults.colors(indicatorColor = SecondaryColor),
                        selected = (currentRoute == Screens.ScreenUser.route),
                        onClick = {
                            if (currentRoute != Screens.ScreenUser.route) {
                                navController.navigate(Screens.ScreenUser.route) {
                                    popUpTo(navController.graph.startDestinationId)
                                    launchSingleTop = true

                                }
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = null
                            )
                        })

                }
            }
        }

    ) { innerPadding ->
        AppNavigation(navController = navController, Modifier.padding(innerPadding))

    }

}