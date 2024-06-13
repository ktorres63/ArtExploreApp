package com.danp.artexploreapp.ui.view

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


@Composable
fun Login(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "LOGIN", fontSize = 30.sp, color = GreenJC)

            Button(onClick = {
                navController.navigate(Screens.ScreenHome.route)
            }) {
                Text(text = "(toHome)")
            }
            Button(onClick = {
                navController.navigate(Screens.ScreenSignUp.route)
            }) {
                Text(text = "SignUp")
            }
        }
    }
}