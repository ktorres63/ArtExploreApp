package com.danp.artexploreapp.qr.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.danp.artexploreapp.util.MyTopBar


@Composable
fun QrScreen(navController: NavController) {
    Scaffold(
        topBar = { MyTopBar(navController = navController, header = "QR", isHome = false) }
    ) { ip ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
                .padding(ip)
        ) {
            Text(
                text = "QR Screen"
            )
        }
    }
}