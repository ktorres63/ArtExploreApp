package com.danp.artexploreapp.googleMap.presentation.screens

import android.widget.Button
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.danp.artexploreapp.util.MyTopBar
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


@Composable
fun Map(navController: NavController) {
    Scaffold(
        topBar = { MyTopBar(navController = navController, header = "Mapa", isHome = false) }
    ) { ip ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(ip))
        val centroCulturalunsa = LatLng(-16.398074, -71.537280)
        val salaCulturalMonasterioSC = LatLng(-16.396122, -71.536472)
        val umbralCentroCultural = LatLng(-16.397567, -71.535502)

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
            Marker(
                state = rememberMarkerState(position = umbralCentroCultural),
                title = "Umbral Centro Cultural",
                snippet = "Centro cultural • Calle San Francisco 204,\n" +
                        "interior 110, C. Moral 115",
                onInfoWindowClick = {
                    dialogTitle = "Umbral Centro Cultural"
                    dialogMessage = "Centro cultural • Calle San Francisco 204,Arequipa"
                    showDialog = true
                }
            )
        }

        if (showDialog) {
            AlertDialog( //Cambiar Barra Texto Negro
                onDismissRequest = { showDialog = false },
                title = { Text(text = dialogTitle, color = Color.Black) },
                text = { Text(text = dialogMessage, color = Color.Black) },
                confirmButton = {
                    TextButton(onClick = {
                        navController.navigate(Screens.ScreenMapMuseum.route)
                        showDialog = false
                    }) {
                        Text("IR" )
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("Cancelar")
                    }
                },
                containerColor = Color(0xFFF5F5DC),
                shape = MaterialTheme.shapes.medium
            )
        }
    }
}