package com.danp.artexploreapp.qr.presentation

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.danp.artexploreapp.R
import com.danp.artexploreapp.paiting.presentation.PaintingsViewModel
import com.danp.artexploreapp.ui.theme.SecondaryColor
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
            // Mensaje mejorado para el usuario
            Toast.makeText(context, "Escaneo cancelado. Por favor, intenta de nuevo.", Toast.LENGTH_LONG).show()
        } else {
            val scannedData = result.contents

            // Procesar el texto escaneado para extraer el ID
            val lines = scannedData.split("\n") // Dividir el texto en líneas
            if (lines.isNotEmpty()) {
                val idLine = lines[0].trim() // Tomar la primera línea
                if (idLine.startsWith("id:")) {
                    val scannedId = idLine.substringAfter("id:").trim() // Extraer el número del ID

                    val painting = paintingsViewModel.getPaintingById(scannedId)
                    if (painting != null) {
                        val gson = Gson()
                        // Navegar a la pantalla de la pintura
                        Log.i("EJEM", painting.toString())
                        val paintingJson = gson.toJson(painting)
                        navController.navigate("paintingView/${Uri.encode(paintingJson)}")
                    } else {
                        // Mensaje más descriptivo si no se encuentra la pintura
                        Toast.makeText(
                            context,
                            "No se encontró la pintura con ID: $scannedId. Por favor, verifica el código QR.",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } else {
                    // Mostrar un mensaje si el formato del QR no es correcto
                    Toast.makeText(
                        context,
                        "Error al reconocer el QR. Asegúrese de que sea de una obra de arte.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } else {
                // Mensaje para el caso donde el escaneo no contiene texto
                Toast.makeText(
                    context,
                    "El código QR escaneado no contiene información válida.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    Scaffold(
        topBar = { MyTopBar(navController = navController, header = "Escanear QR", isHome = false) }
    ) { ip ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(ip),
            verticalArrangement = Arrangement.Center, // Centra verticalmente
            horizontalAlignment = Alignment.CenterHorizontally // Centra horizontalmente
        ) {
            // Envolviendo la imagen en un Box con clickable
            Box(
                modifier = Modifier
                    .size(300.dp)
                    .padding(12.dp)
                    .clickable {
                        // Iniciar el escáner cuando se toca la imagen
                        val options = ScanOptions()
                        options.setDesiredBarcodeFormats(ScanOptions.QR_CODE) // Solo escanea códigos QR
                        options.setPrompt("Por favor, coloca el código QR en el centro de la pantalla para escanear")
                        options.setBeepEnabled(true)
                        options.setCaptureActivity(CaptureActivityPortrait::class.java)
                        options.setOrientationLocked(false)

                        barcodeLauncher.launch(options)
                    }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.scan_qr),
                    contentDescription = "Escanear Código QR",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentScale = ContentScale.Fit
                )
            }

            Text(
                text = "Escáner QR",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp)
            )

            Text(
                text = "Escanea un código QR para descubrir más información de la obra de arte.",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            // Botón para iniciar el escaneo
            Button(
                onClick = {
                    val options = ScanOptions()
                    options.setDesiredBarcodeFormats(ScanOptions.QR_CODE) // Solo escanea códigos QR
                    options.setPrompt("Por favor, coloca el código QR en el centro de la pantalla para escanear")
                    options.setBeepEnabled(true)
                    options.setCaptureActivity(CaptureActivityPortrait::class.java)
                    options.setOrientationLocked(false)

                    barcodeLauncher.launch(options)
                },
                colors = ButtonDefaults.buttonColors(containerColor = SecondaryColor),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Escanear Código QR", color = Color.White, fontSize = 18.sp)
            }
        }
    }
}
