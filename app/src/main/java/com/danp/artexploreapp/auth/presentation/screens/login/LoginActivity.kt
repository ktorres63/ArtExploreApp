package com.danp.artexploreapp.auth.presentation.screens.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.danp.artexploreapp.auth.presentation.viewModels.AuthViewModel
import com.danp.artexploreapp.ui.theme.ArtExploreAppTheme
import com.danp.artexploreapp.util.MainActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtExploreAppTheme {
                val auth = FirebaseAuth.getInstance()
                //composable(route = Screens.ScreenLogin2.route) { Login(navController, AuthViewModel(auth)) }
                Login(onLoginSuccess = ::navigateToMainApp , AuthViewModel(auth) )
            }
        }
    }
    private fun navigateToMainApp() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}