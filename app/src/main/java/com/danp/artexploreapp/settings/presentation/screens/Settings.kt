package com.danp.artexploreapp.settings.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.danp.artexploreapp.ui.theme.GreenJC
import com.danp.artexploreapp.util.MyTopBar


@Composable
fun Settings(navController: NavController) {
    Scaffold(
        topBar = {
            MyTopBar(
                navController = navController,
                header = "Configuraciones",
                isHome = false
            )
        }
    ) { ip ->


        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(ip)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Setttings", fontSize = 30.sp, color = GreenJC)
            }
        }
    }
}