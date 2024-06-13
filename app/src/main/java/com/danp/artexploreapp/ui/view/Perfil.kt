package com.danp.artexploreapp.ui.view

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.danp.artexploreapp.R
import com.danp.artexploreapp.ui.theme.PinkNav

/*
@Preview(showBackground = true)  //BORRAR AL MANDAR
@Composable
fun Perfil() {

    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Perfil", fontSize = 30.sp, color = GreenJC)
            //TODO
        }
    }
 val icons = listOf(Icons.Filled.Favorite, Icons.Filled.Share, Icons.Filled.Settings)
 val labels = listOf("Your Favorite", "Share App", "Settings")
*/

@Composable
fun Perfil(navController: NavController) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProfileSection()
        HistorySection()
        OptionsSection(context)
        LogOutButton()
    }
}

@Composable
fun ProfileSection() {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "Profile image",
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(16.dp)) // Espacio entre la imagen y el texto

        // Textos a la izquierda
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Ayrton Garcia",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = PinkNav
            )
            Text(
                text = "Estudiante",
                fontSize = 16.sp,
                color = Color.Gray
            )
            Text(
                text = "Activo",
                fontSize = 16.sp,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun HistorySection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Text(
            text = "History",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = PinkNav,
            modifier = Modifier.align(Alignment.Start)
        )
        LazyRow(
            modifier = Modifier.padding(top = 8.dp)
        ) {
            items(8) { // Ajusta el número de elementos según tu necesidad
                Image(
                    painter = painterResource(id = R.drawable.history_image), // Usa tu recurso de imagen de historial aquí
                    contentDescription = "History image",
                    modifier = Modifier
                        .size(80.dp)
                        .padding(4.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

@Composable
fun OptionsSection(context: android.content.Context) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        OptionItem(icon = Icons.Filled.Favorite, label = "Your Favorite")
        OptionItem(icon = Icons.Filled.Share, label = "Share App")
        OptionItem(
            icon = Icons.Filled.Settings,
            label = "Settings",
            onClick = {
                //TODO
            }
        )
    }
}

@Composable
fun OptionItem(icon: ImageVector, label: String, onClick: (() -> Unit)? = null) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick ?: {})
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = PinkNav,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = label,
            fontSize = 16.sp,
            color = Color.Black
        )
    }
}

@Composable
fun LogOutButton() {
    Button(
        onClick = { /* Handle log out button click */ },
        colors = ButtonDefaults.buttonColors(containerColor = PinkNav)
    ) {
        Text("Log Out", color = Color.White)
    }
}