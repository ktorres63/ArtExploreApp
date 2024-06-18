package com.danp.lab05_danp_canvas.presentation.screen

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.danp.lab05_danp_canvas.R
import com.danp.lab05_danp_canvas.presentation.viewmodel.GalleryViewModel



@SuppressLint("RememberReturnType")
@Composable
fun Grito(viewModel: GalleryViewModel,context: Context) {
  val mp: MediaPlayer = MediaPlayer.create(context, R.raw.hola)

    Dialog(onDismissRequest = { viewModel.onChangeShowDialog(false) }) {
        Box(
            modifier = Modifier
                .size(700.dp)
                .background(Color.Black),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "EL GRITO",
                    style = TextStyle(color = Color.White, fontSize = 32.sp),
                    modifier = Modifier.padding(top = 16.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.grito),
                    contentDescription = "Imagen de un artista famoso",
                    modifier = Modifier.size(200.dp)
                )
                Text(
                    text = "El grito es el título de cuatro cuadros del noruego Edvard Munch. La versión más famosa se encuentra en la Galería Nacional de Noruega y fue completada en 1893. Otras dos versiones del cuadro se encuentran en el Museo Munch, también en Oslo, mientras que la cuarta versión pertenece a una colección particular. En 1895, Munch realizó también una litografía con el mismo título",
                    style = TextStyle(color = Color.White, fontSize = 12.sp,  fontFamily = FontFamily.Default),
                    modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 16.dp) ,
                    textAlign = TextAlign.Justify,


                )
                Button(
                    onClick = { mp.start()},
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
}