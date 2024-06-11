package com.danp.artexploreapp.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.danp.artexploreapp.ui.theme.ArtExploreAppTheme
import com.danp.artexploreapp.ui.theme.BoneJC
import com.danp.artexploreapp.ui.theme.PinkNav

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtExploreAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyBottomAppBar()

                }
            }
        }
    }
}

@Composable
fun MyBottomAppBar() {
    val navigationController = rememberNavController()
    val context = LocalContext.current.applicationContext
    val selected = remember {
        mutableStateOf(Icons.Default.Home)
    }
    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = BoneJC
            ) {
                IconButton(onClick = {
                    selected.value = Icons.Default.Home
                    navigationController.navigate(Screens.Home.screen) {
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
                IconButton(onClick = {
                    selected.value = Icons.Default.LocationOn
                    navigationController.navigate(Screens.Map.screen) {
                        popUpTo(0)
                    }
                }, modifier = Modifier.weight(1f)) {
                    Icon(
                        Icons.Default.LocationOn,
                        contentDescription = null,
                        modifier = Modifier.size(26.dp),
                        tint = if (selected.value == Icons.Default.LocationOn) PinkNav else Color.DarkGray
                    )
                }
                IconButton(onClick = {
                    selected.value = Icons.Default.Person
                    navigationController.navigate(Screens.Perfil.screen) {
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
            startDestination = Screens.Home.screen,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screens.Home.screen){ Home() }
            composable(Screens.Map.screen){ Map() }
            composable(Screens.Perfil.screen){ Perfil() }


        }
    }
}
