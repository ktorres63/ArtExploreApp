package com.danp.artexploreapp.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import com.idnp2024a.beaconscanner.BeaconScanerLibrary.Beacon

class BeaconScannerService : Service() {
    private val TAG = "BeaconScannerService"
    private val binder = MyBinder()

    private var currentGallery: String? = "Galeria Actual - ejemplo"
    private var nearestPainting: String? = "Pintura cercada - ejemplo"



    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "OnCreate BeaconScannerService")


    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy BeaconScannerService")


    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
        Log.d(TAG, "onStartCommand BeaconScannerService")
    }
    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }


    // Metodo publicos
    fun getCurrentGallery(): String? {
        return currentGallery
    }

    // Metodos publicos
    fun getNearestPainting(): String? {
        return nearestPainting
    }

    // metodos auxiciliares
    private fun getGalleryFromMajor(major: Int?): String {
        // Implementa la lógica para mapear Major a galerías
        return when (major) {
            1 -> "Galería A"
            2 -> "Galería B"
            3 -> "Galería C"
            else -> "Desconocido"
        }
    }

    private fun getPaintingFromMinor(major: Int, minor: Int): String {
        // Implementa la lógica para mapear Minor a pinturas específicas dentro de una galería
        return "Pintura $minor en Galería $major"
    }

    private fun handleBeacons(beacons: Collection<Beacon>) {
        // Implementa la lógica para determinar la galería y la pintura más cercana
        // Ejemplo simplificado:
        var closestBeacon: Beacon? = null
        var closestDistance = Double.MAX_VALUE
        for (beacon in beacons) {
            val distance = beacon.distance
            if (distance != null) {
                if (distance < closestDistance) {
                    closestDistance = distance
                    closestBeacon = beacon
                }
            }
        }

        closestBeacon?.let {
            val major = it.major
            val minor = it.minor
            if(major!= null && minor!=null){
                currentGallery = getGalleryFromMajor(major)
                nearestPainting = getPaintingFromMinor(major, minor)
            }
        }
    }



    // Clase interna que extiende Binder y define métodos públicos del servicio
    inner class MyBinder : Binder() {
        fun getService(): BeaconScannerService {
            return this@BeaconScannerService
        }
    }
}