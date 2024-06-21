package com.danp.artexploreapp.artRoom.presentation.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import com.danp.artexploreapp.util.MyTopBar
import kotlinx.coroutines.delay

@Composable
fun Room1(navController: NavController, viewModel: ArtRoomViewModel) {
    val showDialog1 = viewModel.showDialog1
    val showDialog2 = viewModel.showDialog2
    val circlePosition = viewModel.circlePosition


    val colorBackground = Color.White
    val (screenWidth, screenHeight) = getScreenDimensions()
    var iconBoxSize1 by remember { mutableStateOf(70.dp) }
    var iconBoxSize2 by remember { mutableStateOf(70.dp) }
    var iconBoxSize3 by remember { mutableStateOf(70.dp) }
    var iconBoxSize4 by remember { mutableStateOf(70.dp) }
    var iconBoxSize5 by remember { mutableStateOf(70.dp) }

    val iconBoxOffsetX1 = (-150.dp)
    val iconBoxOffsetY1 = (-120.dp)
    val iconBoxOffsetX2 = (150.dp)
    val iconBoxOffsetY2 = (0.dp)
    val iconBoxOffsetX3 = (-150.dp)
    val iconBoxOffsetY3 = (-550.dp)
    val iconBoxOffsetX4 = (150.dp)
    val iconBoxOffsetY4 = (-350.dp)
    val iconBoxOffsetX5 = (150.dp)
    val iconBoxOffsetY5 = (-650.dp)
    //candelabros
    val iconBoxOffsetXCandelabro1 = (0.dp)
    val iconBoxOffsetYCandelabro1 = (-500.dp)

    val iconBoxOffsetXCandelabro2 = (0.dp)
    val iconBoxOffsetYCandelabro2 = (-150.dp)
    //LaunchedEffect(Unit) {
    //   delay(10000)
    //   iconBoxSize1 = 100.dp
    //}

    // LaunchedEffect to change the size of IconBox1 after 10 seconds

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
            IconBox(
                viewModel = viewModel,
                offsetX = iconBoxOffsetX1,
                offsetY = iconBoxOffsetY1,
                size1 = iconBoxSize1,
                navController = navController,
                modifier = Modifier.align(Alignment.BottomEnd)
            )
            IconBox2(
                viewModel = viewModel,
                offsetX = iconBoxOffsetX2,
                offsetY = iconBoxOffsetY2,
                size2 = iconBoxSize2,
                navController = navController,
                modifier = Modifier.align(Alignment.BottomEnd)
            )
            IconBox3(
                viewModel = viewModel,
                offsetX = iconBoxOffsetX3,
                offsetY = iconBoxOffsetY3,
                size3 = iconBoxSize3,
                navController = navController,
                modifier = Modifier.align(Alignment.BottomEnd)
            )
            IconBox4(
                viewModel = viewModel,
                offsetX = iconBoxOffsetX4,
                offsetY = iconBoxOffsetY4,
                size4 = iconBoxSize4,
                navController = navController,
                modifier = Modifier.align(Alignment.BottomEnd)
            )
            IconBox5(
                viewModel = viewModel,
                offsetX = iconBoxOffsetX5,
                offsetY = iconBoxOffsetY5,
                size5 = iconBoxSize5,
                navController = navController,
                modifier = Modifier.align(Alignment.BottomEnd)
            )
            IconBox6(
                viewModel = viewModel,
                offsetX = iconBoxOffsetXCandelabro1,
                offsetY = iconBoxOffsetYCandelabro1,

                navController = navController,
                modifier = Modifier.align(Alignment.BottomEnd)
            )
            IconBox7(
                viewModel = viewModel,
                offsetX = iconBoxOffsetXCandelabro2,
                offsetY = iconBoxOffsetYCandelabro2,

                navController = navController,
                modifier = Modifier.align(Alignment.BottomEnd)
            )

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
    viewModel: ArtRoomViewModel,
    offsetX: Dp,
    offsetY: Dp,
    size1: Dp,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .offset(x = offsetX, y = offsetY)
            .padding(60.dp)
            .size(size1)
            .clip(CircleShape)
            .clickable {
                navController.navigate(Screens.ScreenRoom1MapPaint1.route) {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.monalisa),
            contentDescription = "Ícono",
            modifier = Modifier
                .padding(3.dp)
                .size(140.dp)
        )
    }
}

@Composable
fun IconBox2(
    viewModel: ArtRoomViewModel,
    offsetX: Dp,
    offsetY: Dp,
    size2: Dp,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .offset(x = offsetX, y = offsetY)
            .padding(60.dp)
            .size(size2)
            .clip(CircleShape)
            .clickable {
                navController.navigate(Screens.ScreenRoom1MapPaint2.route)
            },
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            painter = painterResource(id = R.drawable.grito),
            contentDescription = "Ícono",
            modifier = Modifier
                .padding(3.dp)
                .size(140.dp)
        )
    }
}

@Composable
fun IconBox3(
    viewModel: ArtRoomViewModel,
    offsetX: Dp,
    offsetY: Dp,
    size3: Dp,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .offset(x = offsetX, y = offsetY)
            .padding(60.dp)
            .size(size3)
            .clip(CircleShape)
            .clickable {
                navController.navigate(Screens.ScreenRoom1MapPaint3.route)
            },
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            painter = painterResource(id = R.drawable.elbeso),
            contentDescription = "Ícono",
            modifier = Modifier
                .padding(3.dp)
                .size(140.dp)
        )
    }
}

@Composable
fun IconBox4(
    viewModel: ArtRoomViewModel,
    offsetX: Dp,
    offsetY: Dp,
    size4: Dp,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .offset(x = offsetX, y = offsetY)
            .padding(60.dp)
            .size(size4)
            .clip(CircleShape)
            .clickable {
                navController.navigate(Screens.ScreenRoom1MapPaint4.route)
            },
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            painter = painterResource(id = R.drawable.nocheestrellada),
            contentDescription = "Ícono",
            modifier = Modifier
                .padding(3.dp)
                .size(140.dp)
        )
    }
}

@Composable
fun IconBox5(
    viewModel: ArtRoomViewModel,
    offsetX: Dp,
    offsetY: Dp,
    size5: Dp,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .offset(x = offsetX, y = offsetY)
            .padding(60.dp)
            .size(size5)
            .clip(CircleShape)
            .clickable {
                navController.navigate(Screens.ScreenRoom1MapPaint5.route)
            },
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            painter = painterResource(id = R.drawable.dama),
            contentDescription = "Ícono",
            modifier = Modifier
                .padding(3.dp)
                .size(140.dp)
        )
    }
}

@Composable
fun IconBox6(
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
fun IconBox7(
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