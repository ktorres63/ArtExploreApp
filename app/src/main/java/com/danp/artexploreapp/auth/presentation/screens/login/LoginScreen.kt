package com.danp.artexploreapp.auth.presentation.screens.login

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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.danp.artexploreapp.auth.presentation.viewModels.LoginViewModel

@Composable
fun LoginScreen(navController: NavHostController, viewModel: LoginViewModel) {
    viewModel.setNavController(navController);
    login(navController, viewModel)

}

@Composable
fun login(navController: NavHostController, viewModel: LoginViewModel) {
    val email: String by viewModel.email.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")
    val loginEnable: Boolean by viewModel.loginEnable.observeAsState(initial = false)
    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)
    val isAcceptTermsCondition: Boolean by viewModel.isAcceptTermsCondition.observeAsState(initial = false)
    val coroutineScope = rememberCoroutineScope()


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
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(
                    color = Color(0xFFEBEBE6),
                    shape = RoundedCornerShape(16.dp) // Redondear las esquinas del fondo

                ) // Fondo diferente para la caja principal
                .fillMaxSize()

        ) {
            var isChecked by remember { mutableStateOf(false) }

//            PreviewButtonChangeLoginSignUp()
            TitleSignUp("Login")
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,

                ) {


                InputsLoginEmail(email) { viewModel.onLoginChanged(it, password) }
                Spacer(modifier = Modifier.height(16.dp))
                InputsLoginPassword(password) { viewModel.onLoginChanged(email, it) }

                CheckAccept(
                    textAccept = "Acepta los términos y condiciones",
                    isChecked = isAcceptTermsCondition,
                    onCheckedChange = { viewModel.onAcceptTermsConditionChanged(!isAcceptTermsCondition) },
                    modifier = Modifier.padding(16.dp)
                )
                ButtonContinue(
                    textContinue = "Continuar",
                    onClick = { viewModel.onClickContinue()  },
                    backgroundColor = Color(0xFFFF6A5F), // Color de fondo personalizado
                    textColor = Color.White, // Color de texto personalizado
                    enabled = viewModel.loginEnable.value ?: false

                )
            }
            ImprovedOtherLogin(
                buttonBackgroundColor = Color.White,
                buttonTextColor = Color(0xFF465A54),
                colorBack = Color(0xFF465A54),
                textColor = Color.White
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewButtonChangeLoginSignUp() {
    var selectedButton by remember { mutableStateOf("Login") } // Estado para controlar el botón seleccionado

    // Botones de inicio de sesión y registro
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 20.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        // Botón de inicio de sesión
        LoginSignUpButton(
            text = "Login",
            onClick = { selectedButton = "Login" }, // Actualizar estado al hacer clic
            backgroundColor = if (selectedButton == "Login") Color(0xFF465A54) else Color.White, // Cambiar color según el estado
            textColor = if (selectedButton == "Login") Color.White else Color.Black
        )// Cambiar color según el estado
        // Botón de registro
        LoginSignUpButton(
            text = "Sign Up",
            onClick = { selectedButton = "SignUp" }, // Actualizar estado al hacer clic
            backgroundColor = if (selectedButton == "SignUp") Color(0xFF465A54) else Color.White, // Cambiar color según el estado
            textColor = if (selectedButton == "SignUp") Color.White else Color.Black
        )
    }
}

@Composable
fun LoginSignUpButton(
    onClick: () -> Unit,
    text: String,
    textColor: Color,
    backgroundColor: Color,
) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = textColor
        ),
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .clip(RoundedCornerShape(16.dp))
            .width(160.dp) // Ancho fijo para los botones
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp)
        )
    }
}

@Composable
fun TitleSignUp(text: String) {
    Text(
        text = text,
        fontSize = 24.sp, // Tamaño de fuente más grande
        fontWeight = FontWeight.Bold, // Estilo en negrita para enfatizar
        textAlign = TextAlign.Center, // Centrar el texto
        modifier = Modifier.padding(vertical = 16.dp) // Agregar espacio vertical
    )
}


@Composable
//@Preview(showBackground = true)
fun InputsLoginEmail(email: String, onTextFieldChanged: (String) -> Unit) {

    Column(modifier = Modifier.padding(top = 16.dp, start = 15.dp, end = 15.dp)) {

        TextField(
            value = email,
            onValueChange = { onTextFieldChanged(it) },
            label = { Text("Email") }
        )
    }
}

@Composable
fun InputsLoginPassword(password: String, onTextFieldChanged: (String) -> Unit) {

    Column(modifier = Modifier.padding(top = 16.dp, start = 15.dp, end = 15.dp)) {
        TextField(
            value = password,
            onValueChange = { onTextFieldChanged(it) },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
    }
}


@Composable
@Preview(showBackground = true)
fun CheckAcceptPreview() {
    var isChecked by remember { mutableStateOf(false) }
    CheckAccept(
        textAccept = "Acepta los términos y condiciones",
        isChecked = isChecked,
        onCheckedChange = { isChecked = it },
        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
    )
}

@Composable
fun CheckAccept(
    textAccept: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = onCheckedChange
        )
        Text(
            text = textAccept,
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically)
        )
    }
}


@Composable
fun ButtonContinue(
    textContinue: String,
    onClick: () -> Unit,
    backgroundColor: Color = Color.Blue, // Color de fondo predeterminado
    textColor: Color = Color.White, // Color de texto predeterminado
    enabled: Boolean = true // Estado habilitado predeterminado

) {
    Button(
        onClick = { onClick() },
        modifier = Modifier
            .fillMaxWidth() // Ocupar todo el ancho disponible
            .padding(horizontal = 50.dp, vertical = 8.dp), // Añadir relleno
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor), // Color de fondo personalizado
        enabled = enabled
    ) {
        Text(
            text = textContinue,
            color = textColor // Color de texto personalizado
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ImprovedOtherLogin(
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
