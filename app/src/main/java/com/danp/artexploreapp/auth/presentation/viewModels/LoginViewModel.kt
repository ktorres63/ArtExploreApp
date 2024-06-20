package com.danp.artexploreapp.auth.presentation.viewModels

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.danp.artexploreapp.util.navigation.Screens
import kotlinx.coroutines.delay

class LoginViewModel : ViewModel() {
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _loginEnable = MutableLiveData<Boolean>()
    val loginEnable: LiveData<Boolean> = _loginEnable

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isAcceptTermsCondition = MutableLiveData<Boolean>()
    val isAcceptTermsCondition: LiveData<Boolean> = _isAcceptTermsCondition

    private var navController: NavController? = null

    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    fun onAcceptTermsConditionChanged(isAcceptTermsCondition: Boolean) {
        _isAcceptTermsCondition.value = isAcceptTermsCondition;
    }

    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
        _loginEnable.value = isValidEmail(email) && isValidPassword(password)
    }

    private fun isValidPassword(password: String): Boolean = password.length > 6

    private fun isValidEmail(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()

    suspend fun onLoginSelected() {
        _isLoading.value = true
        delay(4000)
        _isLoading.value = false
    }

    fun onClickContinue() {
        Log.d("LoginViewModel", "Email: ${_email.value}, Password: ${_password.value}")
//        navController?.navigate(Screens.ScreenHome.route)()
        navController?.navigate(Screens.ScreenHome.route)

    }
}
