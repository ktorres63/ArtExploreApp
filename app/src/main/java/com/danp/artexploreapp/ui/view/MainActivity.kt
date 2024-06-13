package com.danp.artexploreapp.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.danp.artexploreapp.R
import com.danp.artexploreapp.ui.theme.ArtExploreAppTheme
import com.danp.artexploreapp.ui.theme.BoneJC
import com.danp.artexploreapp.ui.theme.PinkNav

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            ArtExploreAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(navController = navController)
                }
            }
        }
    }
}
/*
@Composable
fun BottomAppBar() {
    val navigationController = rememberNavController()
    val selected = remember {
        mutableStateOf(Icons.Default.Home)
    }
    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = BoneJC
            ) {
                // Home
                IconButton(onClick = {
                    selected.value = Icons.Default.Home
                    navigationController.navigate(Screens.ScreenHome.route) {
                        popUpTo(0)
                    }
                }, modifier = Modifier.weight(1f)) {
                    Icon(
                        Icons.Default.Home,
                        contentDescription = null,
                        modifier = Modifier.size(26.dp),
                        tint = if (selected.value == Icons.Default.Home) PinkNav else Color.DarkGray
                    )
                }
                //QR
                IconButton(onClick = {
                    selected.value = Icons.Default.ExitToApp
                    navigationController.navigate(Screens.ScreenMap.route) {
                        popUpTo(0)
                    }
                }, modifier = Modifier.weight(1f)) {
                    Icon(
                        painterResource(id = R.drawable.ic_qr), //QR
                        contentDescription = null,
                        modifier = Modifier.size(26.dp),
                        tint = if (selected.value == Icons.Default.LocationOn) PinkNav else Color.DarkGray
                    )
                }
                IconButton(onClick = {
                    selected.value = Icons.Default.LocationOn
                    navigationController.navigate(Screens.ScreenMap.route) {
                        popUpTo(0)
                    }
                }, modifier = Modifier.weight(1f)) {
                    Icon(
                        Icons.Default.LocationOn, //Map
                        contentDescription = null,
                        modifier = Modifier.size(26.dp),
                        tint = if (selected.value == Icons.Default.LocationOn) PinkNav else Color.DarkGray
                    )
                }
                IconButton(onClick = {
                    selected.value = Icons.Default.Person
                    navigationController.navigate(Screens.ScreenUser.route) {
                        popUpTo(0)
                    }
                }, modifier = Modifier.weight(1f)) {
                    Icon(
                        Icons.Default.Person,
                        contentDescription = null,
                        modifier = Modifier.size(26.dp),
                        tint = if (selected.value == Icons.Default.Person) PinkNav else Color.DarkGray
                    )
                }

            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navigationController,
            startDestination = Screens.ScreenHome.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screens.ScreenHome.route) { Home(navController = navigationController ) }
            composable(Screens.ScreenMap.route) { Map() }
            composable(Screens.ScreenUser.route) { Perfil() }
        }
    }
}
*/