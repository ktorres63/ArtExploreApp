package com.danp.artexploreapp.ui.view
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.danp.artexploreapp.R
import com.danp.artexploreapp.ui.viewmodel.GalleryViewModel


@Composable
fun NocheEstrelladaDialogScreen(viewModel: GalleryViewModel) {

    Dialog(onDismissRequest = { viewModel.onChangeShowDialog(false) }) {
        Box(
            modifier = Modifier
                .size(300.dp)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.monalisa),
                contentDescription = "Imagen de un artista famoso",
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}