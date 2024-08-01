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
import com.danp.artexploreapp.paiting.domain.Painting
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
        handleScanResult(result.contents, paintingsViewModel, navController, context)
    }

    Scaffold(
        topBar = { MyTopBar(navController = navController, header = "Escanear QR", isHome = false) }
    ) { ip ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(ip),
            verticalArrangement = Arrangement.Top, // Centra verticalmente
            horizontalAlignment = Alignment.CenterHorizontally // Centra horizontalmente
        ) {
            ScannerText()
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(ip),
            verticalArrangement = Arrangement.Center, // Centra verticalmente
            horizontalAlignment = Alignment.CenterHorizontally // Centra horizontalmente
        ) {
            QrScannerImage { launchQrScanner(barcodeLauncher) }
            ScanButton { launchQrScanner(barcodeLauncher) }
        }
    }
}

private fun handleScanResult(
    scannedData: String?,
    paintingsViewModel: PaintingsViewModel,
    navController: NavController,
    context: android.content.Context
) {
    // Handle the case where the scanned data is null
    if (scannedData.isNullOrEmpty()) {
        showToast(context, "Escaneo cancelado. Por favor, intenta de nuevo.")
        return
    }

    // Extract the ID from the scanned data
    val idNumber = extractIdFromScannedData(scannedData)
    if (idNumber == null || idNumber < 0) {
        showToast(context, "Error al reconocer el QR de la obra de arte")
        return
    }

    // Fetch the painting by ID
    val painting = paintingsViewModel.getPaintingById(idNumber.toString())
    if (painting != null) {
        navigateToPaintingView(navController, painting)
    } else {
        showToast(context, "No se encontró la obra de arte")
    }
}

// Helper function to show toast messages
private fun showToast(context: android.content.Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

// Extract ID from the scanned data
private fun extractIdFromScannedData(scannedData: String): Int? {
    val lines = scannedData.split("\n")
    if (lines.isNotEmpty()) {
        val idLine = lines[0].trim()
        if (idLine.startsWith("id:")) {
            return idLine.substringAfter("id:").trim().toIntOrNull()
        }
    }
    return null
}

// Navigate to the painting view screen
private fun navigateToPaintingView(navController: NavController, painting: Painting) {
    val gson = Gson()
    val paintingJson = gson.toJson(painting)
    Log.i("EJEM", painting.toString())
    navController.navigate("paintingView/${Uri.encode(paintingJson)}")
}


@Composable
private fun QrScannerImage(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(400.dp)
            .padding(12.dp)
            .clickable { onClick() }
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
}

@Composable
private fun ScannerText() {
    Text(
        text = "Escaner QR de la obra de Arte",
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
}

@Composable
private fun ScanButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = SecondaryColor),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        Text("Escanear Código QR", color = Color.White, fontSize = 18.sp)
    }
}

private fun launchQrScanner(barcodeLauncher: androidx.activity.result.ActivityResultLauncher<ScanOptions>) {
    val options = ScanOptions()
    options.setDesiredBarcodeFormats(ScanOptions.QR_CODE) // Solo escanea códigos QR
    options.setPrompt("Por favor, coloca el código QR en el centro de la pantalla para escanear")
    options.setBeepEnabled(true)
    options.setCaptureActivity(CaptureActivityPortrait::class.java)
    options.setOrientationLocked(false)

    barcodeLauncher.launch(options)
}
