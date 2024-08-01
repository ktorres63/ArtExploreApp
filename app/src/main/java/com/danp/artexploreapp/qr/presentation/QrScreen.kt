package com.danp.artexploreapp.qr.presentation

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.danp.artexploreapp.paiting.presentation.PaintingsViewModel
import com.danp.artexploreapp.util.MyTopBar
import com.google.gson.Gson
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

@Composable
fun QrScreen(navController: NavController, paintingsViewModel: PaintingsViewModel) {
    val context = LocalContext.current

    // Registrar el lanzador y el manejador de resultados
    val barcodeLauncher = rememberLauncherForActivityResult(ScanContract()) { result ->
        if (result.contents == null) {
            Toast.makeText(context, "Cancelled", Toast.LENGTH_LONG).show()
        } else {
            val scannedId = result.contents
            val painting = paintingsViewModel.getPaintingById(scannedId)
            if (painting != null) {
                val gson = Gson()
                // Navegar a la pantalla de la pintura
                Log.i("EJEM", painting.toString())
                val paintingJson = gson.toJson(painting)
                navController.navigate("paintingView/${Uri.encode(paintingJson)}")

//            navController.navigate("paintingView/${painting.id}")
            } else {
                Toast.makeText(context, "No se encontró la pintura con ID: $scannedId", Toast.LENGTH_LONG).show()
            }
        }
    }

    Scaffold(
        topBar = { MyTopBar(navController = navController, header = "QR", isHome = false) }
    ) { ip ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(ip)
                .wrapContentSize(Alignment.Center)
        ) {
            Text(
                text = "QR Screen"
            )

            // Botón para iniciar el escaneo
            Button(onClick = {
                val options = ScanOptions()
                options.setDesiredBarcodeFormats(ScanOptions.QR_CODE) // Solo escanea códigos QR
                options.setPrompt("Scan a QR code")
                options.setBeepEnabled(true)
                options.setCaptureActivity(CaptureActivityPortrait::class.java)
                options.setOrientationLocked(false)

                barcodeLauncher.launch(options)
            }) {
                Text("Scan QR Code")
            }
        }
    }
}
