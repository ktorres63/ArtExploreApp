package com.danp.artexploreapp.beacon_position_scanner


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.danp.artexploreapp.R
import com.danp.artexploreapp.beacon_position_scanner.viewModel.MarkerViewModel

object ProjSizes {
    val heightCM = 810.dp
    val widthCM = 765.dp
    const val proportion = 2.22f
    val heightL: Dp = heightCM / proportion
    val widthL: Dp = widthCM / proportion
}

@Composable
fun DpToPx(dp: Dp): Float {
    val density = LocalDensity.current
    return remember(dp, density) {
        with(density) { dp.toPx() }
    }
}

@Composable
fun Final(navController: NavController, viewModel: MarkerViewModel = MarkerViewModel()) {
    val switchState = viewModel.getSwitchState().observeAsState(false)
    viewModel.setContex(LocalContext.current)
    val statusMessage = viewModel.statusMessage.observeAsState("No datos se inicio")


    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.arrowellow),
                    contentDescription = "Volver",
                    modifier = Modifier.size(40.dp)
                )
            }

            Switch(
                checked = switchState.value,
                onCheckedChange = { isChecked ->
                    viewModel.onChangeSwitchState(isChecked)
                },
                modifier = Modifier.padding(16.dp)
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .padding(top = 30.dp, end = 20.dp, start = 20.dp)
            ) {
                RoomCanvas()
                // Obtenemos las posiciones del ViewModel
                TargetPos(offset = Offset(DpToPx(viewModel.posX / ProjSizes.proportion), DpToPx(viewModel.posY / ProjSizes.proportion)))
            }
            // Spacer para alinear el botón en la parte inferior
            Text(
                text = statusMessage.value ?: "No status",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
//            Button(
//                onClick = {
//                    // Generamos nuevas posiciones aleatorias
//                    val newPosX = (0..ProjSizes.widthCM.value.toInt() - 1).random().dp
//                    val newPosY = (0..ProjSizes.heightCM.value.toInt() - 1).random().dp
//                    // Actualizamos las posiciones del marcador a través del ViewModel
//                    viewModel.updatePosition(newPosX, newPosY)
//                },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp)
//            ) {
//                Text("Mover Marcador")
//            }
        }
    }
}
@Composable
fun RoomCanvas(modifier: Modifier = Modifier) {
    Canvas(modifier) {
        drawRect(
            color = Color.LightGray,
            size = Size(ProjSizes.widthL.toPx(), ProjSizes.heightL.toPx())
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RoomPreview() {
    RoomCanvas()
}

@Composable
fun TargetPos(offset: Offset, modifier: Modifier = Modifier) {
    Canvas(modifier) {
        drawCircle(Color.Blue, radius = 35f, center = offset)
    }
}

