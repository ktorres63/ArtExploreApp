package com.danp.artexploreapp.artRoom.presentation.screens

import android.net.Uri
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.danp.artexploreapp.R
import com.danp.artexploreapp.paiting.presentation.Grito
import com.danp.artexploreapp.paiting.presentation.NocheEstrelladaDialogScreen
import com.danp.artexploreapp.artRoom.presentation.viewModels.ArtRoomViewModel
import com.danp.artexploreapp.util.navigation.Screens


import androidx.compose.runtime.*
import coil.compose.AsyncImage
import com.danp.artexploreapp.paiting.domain.Painting
import com.danp.artexploreapp.paiting.presentation.PaintingsViewModel
import com.danp.artexploreapp.ui.theme.SecondaryColor
import com.danp.artexploreapp.util.MyTopBar
import com.google.gson.Gson
import kotlinx.coroutines.delay


@Composable
fun Room1(navController: NavController, viewModel: ArtRoomViewModel, paintingsViewModel: PaintingsViewModel) {
    val showDialog1 = viewModel.showDialog1
    val showDialog2 = viewModel.showDialog2
    val circlePosition = viewModel.circlePosition
    val agentsData = paintingsViewModel.agentsData.collectAsState()
    val isLoading = paintingsViewModel.isLoading.collectAsState()
    val iconBoxOffsetXCandelabro1 = (0.dp)
    val iconBoxOffsetYCandelabro1 = (-500.dp)

    val iconBoxOffsetXCandelabro2 = (0.dp)
    val iconBoxOffsetYCandelabro2 = (-150.dp)
    val colorBackground = Color.White

    Scaffold(
        topBar = {
            MyTopBar(
                navController = navController,
                header = "Habitacion 1",
                isHome = false
            )
        }
    ) { ip ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colorBackground)
                .padding(ip),
            contentAlignment = Alignment.BottomCenter
        ) {
            DrawingCanvas(circlePosition)
            if (isLoading.value) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center), color = SecondaryColor)
            } else {
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalItemSpacing = 10.dp
                ) {
                    items(agentsData.value.take(8)) { painting ->
                        IconBox(
                            painting = painting,
                            navController = navController
                        )
                    }
                }
            }
        }



        if (showDialog1) {
            NocheEstrelladaDialogScreen(viewModel)
        }
        if (showDialog2) {
            Grito(viewModel)
        }
    }
}

@Composable
fun IconBox(
    painting: Painting,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(60.dp)
            .size(70.dp)
            .clip(CircleShape)
            .clickable {
                val gson = Gson()
                val paintingJson = Uri.encode(gson.toJson(painting))
                navController.navigate("paintingView/$paintingJson") {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            },
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = painting.imageURL,
            contentDescription = "Ícono",
            modifier = Modifier
                .padding(3.dp)
                .size(140.dp)
        )
    }
}


@Composable
fun IconBox6( //candelabro
    viewModel: ArtRoomViewModel,
    offsetX: Dp,
    offsetY: Dp,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .offset(x = offsetX, y = offsetY)
            .padding(60.dp)
            .size(100.dp)
            .clip(CircleShape),
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            painter = painterResource(id = R.drawable.candelabro),
            contentDescription = "Ícono",
            modifier = Modifier
                .padding(3.dp)
                .size(140.dp)
        )
    }
}

@Composable
fun IconBox7( //candelabro
    viewModel: ArtRoomViewModel,
    offsetX: Dp,
    offsetY: Dp,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .offset(x = offsetX, y = offsetY)
            .padding(60.dp)
            .size(100.dp)
            .clip(CircleShape),
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            painter = painterResource(id = R.drawable.candelabro),
            contentDescription = "Ícono",
            modifier = Modifier
                .padding(3.dp)
                .size(140.dp)
        )
    }
}


@Composable
fun DrawingCanvas(circlePosition: Offset) {
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .padding(35.dp)
    ) {
        val rectPadding = 35.dp.toPx()
        val rectWidth = size.width - 2 * rectPadding
        val rectHeight = size.height - 2 * rectPadding

        // Dibuja el rectángulo grande
        drawRect(
            color = Color.Black,
            topLeft = Offset(rectPadding, rectPadding),
            size = Size(rectWidth, rectHeight),
            style = Stroke(width = 5f)
        )
        drawRect(
            color = Color(0xFFDB073D), // Color de fondo
            topLeft = Offset(rectPadding, rectPadding),
            size = Size(rectWidth, rectHeight)
        )
//PUERTA
        val doorWidth = rectWidth / 20
        val doorHeight = rectHeight / 8
        val doorTopLeft = Offset(rectPadding, rectPadding + rectHeight / 2 - doorHeight / 2)

        drawRect(
            color = Color.Black,
            topLeft = doorTopLeft,
            size = Size(doorWidth, doorHeight),
            style = Stroke(width = 5f)
        )

        drawRect(
            color = Color(0xFF800000), // Color de fondo
            topLeft = doorTopLeft,
            size = Size(doorWidth, doorHeight)
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
        drawRect(
            color = Color(0xFF87CEFA), // Color de fondo
            topLeft = windowTopRight1,
            size = Size(windowWidth, windowHeight)
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
        drawRect(
            color = Color(0xFF87CEFA), // Color de fondo
            topLeft = windowTopRight2,
            size = Size(windowWidth, windowHeight)
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