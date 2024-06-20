package com.danp.artexploreapp.googleMap.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.danp.artexploreapp.ui.theme.GreenJC
import com.danp.artexploreapp.util.navigation.Screens
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

//Nuevas

@Composable
fun Map(navController: NavController) {
    val centroCulturalunsa = LatLng(-16.398074, -71.537280)
    val salaCulturalMonasterioSC = LatLng(-16.396122, -71.536472)

    val properties by remember { mutableStateOf(MapProperties(mapType = MapType.NORMAL)) }
    val uiSettings by remember { mutableStateOf(MapUiSettings(zoomControlsEnabled = true)) }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(-16.397018, -71.537072), 17f)
    }

    var showDialog by remember { mutableStateOf(false) }
    var dialogTitle by remember { mutableStateOf("") }
    var dialogMessage by remember { mutableStateOf("") }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        properties = properties,
        uiSettings = uiSettings,
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = rememberMarkerState(position = centroCulturalunsa),
            title = "Centro cultural UNSA",
            snippet = "Santa Catalina 101, Arequipa 04001",
            onInfoWindowClick = {
                dialogTitle = "Centro cultural UNSA"
                dialogMessage = "Santa Catalina 101, Arequipa 04001"
                showDialog = true
            }
        )
        Marker(
            state = rememberMarkerState(position = salaCulturalMonasterioSC),
            title = "Sala ext Monasterio Santa Catalina",
            snippet = "Santa Catalina 301, Arequipa 04001",
            onInfoWindowClick = {
                dialogTitle = "Sala ext Monasterio Santa Catalina"
                dialogMessage = "Santa Catalina 301, Arequipa 04001"
                showDialog = true
            }
        )
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = dialogTitle) },
            text = { Text(text = dialogMessage) },
            confirmButton = {
                TextButton(onClick = {
                    showDialog = false
                    navController.navigate(Screens.ScreenMapMuseum.route)
                }) {
                    Text("Go there!")
                }
            }
        )
    }
}


//@Preview(showBackground = true)
//@Composable
//fun MapsPreview(){
//    MyGoogleMaps()
//}

//@Composable
//fun Map(navController: NavController) {
//    Box(modifier = Modifier.fillMaxSize()) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .align(Alignment.Center),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text(text = "Map", fontSize = 30.sp, color = GreenJC)
//            Button(onClick = {
//                navController.navigate(Screens.ScreenMapMuseum.route)
//            }) {
//                Text(text = "Museo Unsa")
//            }
//        }
//
//    }
//}

