package com.danp.artexploreapp.artRoom.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.danp.artexploreapp.R
import com.danp.artexploreapp.artRoom.presentation.viewModels.ArtRoomViewModel

@Composable
fun Room1Paint5(navController: NavController, viewModel: ArtRoomViewModel) {
    Box(
        modifier = Modifier
            .size(850.dp)
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {

        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrowellow),
                contentDescription = "Volver",
                modifier = Modifier.size(40.dp)
            )
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "LA JOVEN DE LA PERLA",
                style = TextStyle(color = Color.Black, fontSize = 32.sp),
                modifier = Modifier.padding(top = 16.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.dama),
                contentDescription = "Imagen de un artista famoso",
                modifier = Modifier.size(400.dp)
            )
            Text(
                text = "JOHANNES VERMEER",
                style = TextStyle(color = Color.Black, fontSize = 32.sp),
                modifier = Modifier.padding(top = 14.dp)
            )
            Text(
                text = "La joven de la perla , también conocida como Muchacha con turbante, es una de las obras maestras del pintor neerlandés Johannes Vermeer realizada entre 1665 y 1667. Como el nombre indica, utiliza un pendiente de perla como punto focal. La pintura se encuentra actualmente en el museo Mauritshuis de La Haya.",
                style = TextStyle(color = Color.Black, fontSize = 12.sp, fontFamily = FontFamily.Default),
                modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
                textAlign = TextAlign.Justify,
            )
            Button(
                onClick = { }, // mp.start()
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.iconaudio),
                    contentDescription = "Reproducir audio",
                    modifier = Modifier.size(48.dp)
                )
                Text("Reproducir audio")
            }
        }
    }
}