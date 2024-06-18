package com.danp.artexploreapp.ui.view

import android.content.Context
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.danp.artexploreapp.R
import com.danp.artexploreapp.ui.theme.GreenJC
import com.danp.artexploreapp.ui.viewmodel.GalleryViewModel

@Composable
fun Room1(navController: NavController,viewModel: GalleryViewModel) {
    val showDialog1 = viewModel.showDialog1
    val showDialog2 = viewModel.showDialog2
    val circlePosition = viewModel.circlePosition


    val colorBackground = Color.White
    val (screenWidth, screenHeight) = getScreenDimensions()
    val iconBoxOffsetX1 = (-150.dp)
    val iconBoxOffsetX2 = (150.dp)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorBackground),
        contentAlignment = Alignment.BottomCenter
    ) {
        IconBox(
            viewModel = viewModel,
            offsetX = iconBoxOffsetX1,
            modifier = Modifier.align(Alignment.BottomEnd)
        )
        IconBox2(
            viewModel = viewModel,
            offsetX = iconBoxOffsetX2,
            modifier = Modifier.align(Alignment.BottomEnd)
        )
        DrawingCanvas(circlePosition)
    }

    if (showDialog1) {
        NocheEstrelladaDialogScreen(viewModel)
    }
    if (showDialog2) {
        Grito(viewModel)
    }
}

@Composable
fun IconBox(viewModel: GalleryViewModel, offsetX: Dp, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .offset(x = offsetX)
            .padding(60.dp)
            .size(90.dp)
            .clickable {
                viewModel.onClickIconBox1()
            },
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            painter = painterResource(id = R.drawable.monalisa),
            contentDescription = "Ícono",
            modifier = Modifier.padding(3.dp)
        )
    }
}

@Composable
fun IconBox2(viewModel: GalleryViewModel, offsetX: Dp, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .offset(x = offsetX)
            .padding(60.dp)
            .size(90.dp)
            .clickable {
                viewModel.onClickIconBox2()
            },
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            painter = painterResource(id = R.drawable.grito),
            contentDescription = "Ícono",
            modifier = Modifier.padding(3.dp)
        )
    }
}




@Composable
fun DrawingCanvas(circlePosition: Offset) {
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val rectPadding = 16.dp.toPx()
        val rectWidth = size.width - 2 * rectPadding
        val rectHeight = size.height - 2 * rectPadding

        // Dibuja el rectángulo grande
        drawRect(
            color = Color.Black,
            topLeft = Offset(rectPadding, rectPadding),
            size = Size(rectWidth, rectHeight),
            style = Stroke(width = 5f)
        )


        val doorWidth = rectWidth / 16
        val doorHeight = rectHeight / 5
        val doorTopLeft = Offset(rectPadding, rectPadding + rectHeight / 2 - doorHeight / 2)

        drawRect(
            color = Color.Black,
            topLeft = doorTopLeft,
            size = Size(doorWidth, doorHeight),
            style = Stroke(width = 5f)
        )


        val windowWidth = rectWidth / 18
        val windowHeight = rectHeight / 5
        val rightSideX = rectPadding + rectWidth

        // Ajustar las ventanas un poco más a la derecha
        val windowOffsetX = rectPadding / 1

        // Ventana superior derecha
        val windowTopRight1 = Offset(
            rightSideX - windowWidth - rectPadding + windowOffsetX,
            rectPadding + rectHeight / 4 - windowHeight / 2
        )

        drawRect(
            color = Color.Black,
            topLeft = windowTopRight1,
            size = Size(windowWidth, windowHeight),
            style = Stroke(width = 5f)
        )

        // Ventana inferior derecha
        val windowTopRight2 = Offset(
            rightSideX - windowWidth - rectPadding + windowOffsetX,
            rectPadding + 3 * rectHeight / 4 - windowHeight / 2
        )

        drawRect(
            color = Color.Black,
            topLeft = windowTopRight2,
            size = Size(windowWidth, windowHeight),
            style = Stroke(width = 5f)
        )
    }
}


@Composable
fun getScreenDimensions(): Pair<Float, Float> {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenHeightFloat = LocalDensity.current.run { screenHeight.toPx() }
    val screenWidth = configuration.screenWidthDp.dp
    val screenWidthFloat = LocalDensity.current.run { screenWidth.toPx() }
    return Pair(screenWidthFloat, screenHeightFloat)
}