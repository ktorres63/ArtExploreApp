package com.danp.artexploreapp.qr.presentation

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.danp.artexploreapp.util.MyTopBar
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

@Composable
fun QrScreen(navController: NavController) {
    val context = LocalContext.current

    // Registrar el lanzador y el manejador de resultados
    val barcodeLauncher = rememberLauncherForActivityResult(ScanContract()) { result ->
        if (result.contents == null) {
            Toast.makeText(context, "Cancelled", Toast.LENGTH_LONG).show()
        } else {
//            Toast.makeText(context, "Scanned: " + result.contents, Toast.LENGTH_LONG).show()
            Toast.makeText(context,"Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();

            // Aquí puedes manejar el resultado del código QR escaneado.
            // Por ejemplo, podrías navegar a otra pantalla o actualizar el estado.
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
//                options.setCameraId(0)  // Usa una cámara específica del dispositivo
                options.setBeepEnabled(true)
                options.setCaptureActivity(CaptureActivityPortrait::class.java)
                options.setOrientationLocked(false)
//                options.setBarcodeImageEnabled(true)

                barcodeLauncher.launch(options)
            }) {
                Text("Scan QR Code")
            }
        }
    }
}
