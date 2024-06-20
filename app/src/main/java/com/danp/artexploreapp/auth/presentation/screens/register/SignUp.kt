package com.danp.artexploreapp.auth.presentation.screens.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.danp.artexploreapp.ui.theme.GreenJC
import com.danp.artexploreapp.util.navigation.Screens

@Composable
fun SignUp(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "SigUp", fontSize = 30.sp, color = GreenJC)

            Button(onClick = {
                navController.navigate(Screens.ScreenLogin.route)
            }) {
                Text(text = "(toLogin)")
            }
        }
    }
}