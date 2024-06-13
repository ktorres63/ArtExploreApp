package com.danp.artexploreapp.ui.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.currentBackStackEntryAsState
import com.danp.artexploreapp.R

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
    Scaffold(
        bottomBar = {
            if (currentRoute in bottomBarRoutes) {
                NavigationBar {
                    NavigationBarItem(
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
                                contentDescription = null
                            )
                        })
                    NavigationBarItem(
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
                                contentDescription = null
                            )
                        })
                    NavigationBarItem(
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
                                contentDescription = null
                            )
                        })
                    NavigationBarItem(
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