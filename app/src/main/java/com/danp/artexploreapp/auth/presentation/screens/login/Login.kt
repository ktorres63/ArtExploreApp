package com.danp.artexploreapp.auth.presentation.screens.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.danp.artexploreapp.auth.presentation.viewModels.AuthViewModel
import com.danp.artexploreapp.ui.theme.GreenJC
import com.danp.artexploreapp.util.navigation.Screens


@Composable
fun Login(navController: NavController, viewModel: AuthViewModel) {
    val signInStatus by viewModel.signInStatus.observeAsState(initial = false)

    val baseContext = LocalContext.current
    var isChecked by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                bottom = 20.dp,
                start = 15.dp,
                end = 15.dp,
                top = 30.dp
            ) // Espacio alrededor de la caja principal
    ) {
        Column(
//            verticalArrangement = Arrangement.SpaceAround,
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(
                    color = Color(0xFFEBEBE6),
                    shape = RoundedCornerShape(16.dp) // Redondear las esquinas del fondo

                ) // Fondo diferente para la caja principal
                .fillMaxSize()

        ) {
            Text(
                text = "Login",
                fontSize = 24.sp, // Tamaño de fuente más grande
                fontWeight = FontWeight.Bold, // Estilo en negrita para enfatizar
                textAlign = TextAlign.Center, // Centrar el texto
                modifier = Modifier.padding(vertical = 16.dp) // Agregar espacio vertical
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,

                ) {

                TextField(
                    value = viewModel.email.value,
                    onValueChange = { viewModel.email.value = it },
                    label = { Text("Email") },
                    modifier = Modifier.padding(top = 16.dp, start = 15.dp, end = 15.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = viewModel.password.value,
                    onValueChange = { viewModel.password.value = it },
                    label = { Text(text = "Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)

                )
//                CheckAccept(
//                    textAccept = "Acepta los términos y condiciones",
//                    isChecked = isAcceptTermsCondition,
//                    onCheckedChange = { viewModel.onAcceptTermsConditionChanged(!isAcceptTermsCondition) },
//                    modifier = Modifier.padding(16.dp)
//                )
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { viewModel.signIn() },
                    modifier = Modifier
                        .width(290.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFFFF6A5F)), // Color de fondo personalizado
                ) { Text(text = "Sign In", color = Color.White) }
            }
            OtherLogins(
                buttonBackgroundColor = Color.White,
                buttonTextColor = Color(0xFF465A54),
                colorBack = Color(0xFF465A54),
                textColor = Color.White
            )
        }

    }
    LaunchedEffect(signInStatus) {
        if (signInStatus == true) {
           // Toast.makeText(baseContext,"Success", Toast.LENGTH_SHORT).show()
            navController.navigate(Screens.ScreenHome.route) {
                popUpTo(Screens.ScreenLogin.route) {
                    inclusive = true
                }
            }
        } else {
           // Toast.makeText(baseContext,"Usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show()
        }
    }
  }
@Composable
fun showToast(message: String, show: Boolean) {
    val context = LocalContext.current
    if (show) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}

@Composable
@Preview(showBackground = true)
fun OtherLogins(
    buttonBackgroundColor: Color = Color.Gray, // Color de fondo por defecto
    buttonTextColor: Color = Color.Black, // Color de texto por defecto
    colorBack: Color = Color.LightGray,
    textColor: Color = Color.White
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
//            .height(900.dp)
//            .padding(30.dp)
            .fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceAround,

            modifier = Modifier
                .background(
                    color = colorBack,
                    shape = RoundedCornerShape(16.dp) // Redondear las esquinas del fondo
                )
                .padding(15.dp)
                .height(150.dp)

        ) {

            Text(
                text = "Continue With:",
                fontSize = 20.sp,
                modifier = Modifier.padding(
                    bottom = 20.dp,
                    start = 8.dp,
                    end = 8.dp
                ), // Añadir padding alrededor del texto
                color = textColor
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween // Espacio equitativo entre los botones
            ) {
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.padding(end = 8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = buttonBackgroundColor),
                ) {
                    Text(text = "Google", fontSize = 13.sp, color = buttonTextColor)
                }
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.padding(horizontal = 8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = buttonBackgroundColor),
                ) {
                    Text(text = "Facebook", fontSize = 13.sp, color = buttonTextColor)
                }
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.padding(start = 8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = buttonBackgroundColor),
                ) {
                    Text(text = "Twitter", fontSize = 13.sp, color = buttonTextColor)
                }
            }
        }
    }
}
/*
 if(signInStatus){
 navController.navigate(Screens.ScreenHome.route)
                    }
                    else{
                        Toast.makeText(
                            baseContext,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
 */