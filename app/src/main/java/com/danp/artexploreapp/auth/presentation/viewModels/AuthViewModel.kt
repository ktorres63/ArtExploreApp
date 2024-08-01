package com.danp.artexploreapp.auth.presentation.viewModels

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel(private val auth: FirebaseAuth) : ViewModel() {
    var email = mutableStateOf("")
    var password = mutableStateOf("")

    private val _signInStatus = MutableLiveData<Boolean>()
    val signInStatus: LiveData<Boolean> get() = _signInStatus

    fun signIn(){
        if (email.value.isBlank() || password.value.isBlank()) {
            Log.e("statusLogin", "Authentication failed:failure")
        } else {
            auth.signInWithEmailAndPassword(email.value, password.value)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.i("statusLogin", "signInWithEmail:success")
                        _signInStatus.value = task.isSuccessful

                    } else {
                        Log.e("statusLogin", "Authentication failed:failure", task.exception)

                    }

                }
        }
    }

}