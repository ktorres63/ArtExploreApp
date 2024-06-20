package com.danp.artexploreapp.artRoom.presentation.viewModels


import android.app.Application
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import android.widget.Switch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danp.artexploreapp.services.BeaconScannerService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class ArtRoomViewModel() : ViewModel() {


    lateinit var context : Context


    private val TAG: String = "ArtRoomViewModel"

    private var beaconService: BeaconScannerService? = null

    private var isBound: Boolean = false

    fun setContex(contex: Context){
        this.context = contex
    }
    var switchState by mutableStateOf(false)
        private set
    var showDialog2 by mutableStateOf(false)
        private set
    var showDialog1 by mutableStateOf(false)
        private set
    var circlePosition by mutableStateOf(Offset(300f, 300f))
        private set

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName?, service: IBinder?) {
            val binder = service as BeaconScannerService.MyBinder
            beaconService = binder.getService()
            isBound = true
            startPollingService()

        }

        override fun onServiceDisconnected(className: ComponentName?) {
            beaconService = null
            isBound = false
        }
    }

    fun onChangeSwitchState(newSwitch: Boolean){
        Log.d(TAG, "Change State $switchState -> $newSwitch")

        switchState = newSwitch

        if(switchState){
            startServiceScanBeacon()
        }else{
            stopServiceScanBeacon()
        }
    }

    private fun stopServiceScanBeacon() {
        Log.d(TAG, "Stopping Service Beacon Scanner")

        // stopr service
        if (isBound) {
            context.unbindService(serviceConnection)
            isBound = false
        }
        val intent = Intent(context, BeaconScannerService::class.java)
        context.stopService(intent)
    }

    private fun startServiceScanBeacon() {
        Log.d(TAG, "Starting Service Beacon Scanner")
        // Vincular al servicio
        val intent = Intent(context!!, BeaconScannerService::class.java)
        context!!.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
        context!!.startService(intent)
    }

    private fun startPollingService() {
        viewModelScope.launch(Dispatchers.IO) {
            while (isBound) {
                beaconService?.let { service ->
                    val currentGallery = service.getCurrentGallery()
                    val nearestPainting = service.getNearestPainting()
                    Log.d(TAG, "Current Gallery: $currentGallery")
                    Log.d(TAG, "Nearest Painting: $nearestPainting")
                }
                delay(1000) // Espera 5 segundos antes de la próxima consulta
            }
        }
    }

    fun onChangeShowDialog(newShowDialog: Boolean) {
        showDialog1 = newShowDialog
        showDialog2 = newShowDialog
    }

    fun getRandomPosition(screenWidth: Float, screenHeight: Float, circleRadius: Float): Offset {
        val newX = (Random.nextFloat() * (screenWidth - 2 * circleRadius)) + circleRadius
        val newY = (Random.nextFloat() * (screenHeight - 2 * circleRadius)) + circleRadius
        return Offset(newX, newY)
    }


    fun onClickIconBox1() {
        showDialog1 = true
    }

    fun onClickIconBox2() {
        showDialog2 = true
    }

    fun setNewPositionCircle(newPosition: Offset) {
        circlePosition = newPosition
    }


}
