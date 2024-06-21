package com.danp.artexploreapp.paiting.presentation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import com.danp.artexploreapp.R
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.navigation.NavController
import com.danp.artexploreapp.artRoom.presentation.viewModels.ArtRoomViewModel
import com.danp.artexploreapp.util.navigation.Screens

@Composable
fun Room1Paint1(navController: NavController, viewModel: ArtRoomViewModel) {
   // val mp: MediaPlayer = MediaPlayer.create(context, R.raw.hola)
    Box(
        modifier = Modifier
            .size(850.dp)
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {

        IconButton(
            onClick = { navController.navigate(Screens.ScreenRoom1Map.route) },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp) // Padding para alejar el botón del borde
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrowellow), // Cambia a tu icono de flecha
                contentDescription = "Volver",
                modifier = Modifier.size(40.dp) // Tamaño del icono
            )
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "LA MONALISA",
                style = TextStyle(color = Color.Black, fontSize = 32.sp),
                modifier = Modifier.padding(top = 16.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.monalisa),
                contentDescription = "Imagen de un artista famoso",
                modifier = Modifier.size(400.dp)
            )
            Text(
                text = "LEONARDO DA VINCI",
                style = TextStyle(color = Color.Black, fontSize = 32.sp),
                modifier = Modifier.padding(top = 14.dp)
            )
            Text(
                text = "El retrato de Lisa Gherardini, esposa de Francesco del Giocondo,más conocido como La Gioconda (La Joconde en francés) o Monna Lisa, es una obra pictórica del polímata renacentista italiano Leonardo da Vinci. Fue adquirida por el rey Francisco I de Francia a comienzos del siglo xvi y desde entonces es propiedad del Estado francés. Se halla expuesta en el Museo del Louvre de París, siendo, sin duda, la «joya» de sus colecciones.",
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


