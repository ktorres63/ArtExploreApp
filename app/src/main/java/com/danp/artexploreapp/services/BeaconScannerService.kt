package com.danp.artexploreapp.services

import android.app.Service
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.bluetooth.le.BluetoothLeScanner
import android.bluetooth.le.ScanFilter
import android.bluetooth.le.ScanResult
import android.bluetooth.le.ScanSettings
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Binder
import android.os.IBinder
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.danp.artexploreapp.services.utilsIBeacon.Permission
import com.danp.artexploreapp.services.utilsIBeacon.PermissionManager
import com.danp.artexploreapp.util.MainActivity
import com.idnp2024a.beaconscanner.BeaconScanerLibrary.Beacon
import com.idnp2024a.beaconscanner.BeaconScanerLibrary.BeaconParser
import com.idnp2024a.beaconscanner.BeaconScanerLibrary.BleScanCallback
import com.idnp2024a.beaconscanner.permissions.BTPermissions
import java.util.Timer
import java.util.TimerTask

class BeaconScannerService : Service() {

    private val TAG = "BeaconScannerService"
    private val binder = MyBinder()

    private var currentGallery: String? = "Galeria Actual - ejemplo"
    private var nearestPainting: String? = "Pintura cercada - ejemplo"

    private lateinit var bluetoothManager: BluetoothManager
    private lateinit var btScanner: BluetoothLeScanner
    private lateinit var bluetoothAdapter: BluetoothAdapter
    private lateinit var context: Context // Contexto del servicio
    private val permissionManager = PermissionManager.from()




    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "OnCreate BeaconScannerService")
        context = applicationContext // Asignar el contexto de la aplicación
        initBluetooth()
        startScannerBeacons()
//        checkPermissions()

    }

    private fun initBluetooth() {
        bluetoothManager = getSystemService(BluetoothManager::class.java)
        bluetoothAdapter = bluetoothManager.adapter

        if (bluetoothAdapter != null) {
            btScanner = bluetoothAdapter.bluetoothLeScanner
        } else {
            Log.d(TAG, "BluetoothAdapter is null")
        }
    }

    private fun startScannerBeacons(){
        if(btScanner == null)
            return

        val scanCallBack  = createBleScanCallback();
        Log.i(TAG, "Press start scan button");
        if (!isLocationEnabled() || !isBluetoothEnabled()) {
            Log.d(TAG, "Servicios no activados - La localizacion y el Bluetooth tienen que estar activos");
            return
        }

        bluetoothScanStart(scanCallBack)
    }


    private fun isLocationEnabled(): Boolean {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as android.location.LocationManager
        return locationManager.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            android.location.LocationManager.NETWORK_PROVIDER
        )
    }

    private fun isBluetoothEnabled(): Boolean {
        return bluetoothAdapter.isEnabled
    }

    private fun bluetoothScanStart(bleScanCallback: BleScanCallback) {
        Log.d(TAG, "Starting Bluetooth scan...")
        if (btScanner != null) {
            permissionManager
                .request(Permission.Bluetooth)
                .rationale("Bluetooth permission is needed")
                .checkPermission { isGranted ->
                    if (isGranted) {
                        Log.d(TAG, "Permissions granted, starting scan.")
                        val scanFilter = ScanFilter.Builder()
                            .setManufacturerData(
                                0x004C,
                                byteArrayOf(0x02, 0x15)
                            ) // Ejemplo para iBeacon
                            .build()
                        val scanSettings = ScanSettings.Builder()
                            .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
                            .build()
                        btScanner.startScan(listOf(scanFilter), scanSettings, bleScanCallback)
                    } else {
                        Log.d(TAG, "Bluetooth permission not granted.")
                    }
                }
        } else {
            Log.d(TAG, "BluetoothLeScanner is null")
        }
    }

//
//    private fun checkPermissions() {
//        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION)
//            != PackageManager.PERMISSION_GRANTED
//        ) {
//            // Si el permiso no está concedido, solicitarlo
//            requestPermissions()
//        } else {
//            // Si el permiso está concedido, continuar con la inicialización
//            initBluetooth()
//        }
//    }
//
//    private fun requestPermissions() {
//        // Solicitar permiso de ubicación
////        ActivityCompat.requestPermissions(
//            // Aquí podrías usar una actividad para solicitar los permisos, pero en este caso
//            // se solicitará el permiso al inicio del servicio y se continuará con la inicialización
//            // si el permiso es concedido
////        )
//    }
//

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
//
//
//    private fun startScanTimer() {
//        timer = Timer()
//        timer?.scheduleAtFixedRate(object : TimerTask() {
//            override fun run() {
//                // Ejecutar el escaneo cada vez que se invoque el temporizador
//                if (isBluetoothEnabled() && isLocationEnabled()) {
//                    bluetoothScanStart(createBleScanCallback())
//                } else {
//                    Log.d(TAG, "Bluetooth or Location service is not enabled.")
//                }
//            }
//        }, 0, scanInterval)
//    }
//

//
//    private fun bluetoothScanStop(bleScanCallback: BleScanCallback) {
//        Log.d(TAG, "Stopping Bluetooth scan...")
//        if (btScanner != null) {
//            btScanner.stopScan(bleScanCallback)
//        } else {
//            Log.d(TAG, "BluetoothLeScanner is null")
//        }
//    }




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

    private fun createBleScanCallback(): BleScanCallback {
        return BleScanCallback(
            onScanResultAction,
            onBatchScanResultAction,
            onScanFailedAction
        )
    }

    private val onScanResultAction: (ScanResult?) -> Unit = { result ->
        Log.d(TAG, "onScanResultAction")

        val scanRecord = result?.scanRecord
        val beacon = Beacon(result?.device?.address).apply {
            manufacturer = result?.device?.name
            rssi = result?.rssi
        }
        Log.d(TAG, "Scan: $beacon")

        scanRecord?.bytes?.let {
            val parsedBeacon = BeaconParser.parseIBeacon(it, beacon.rssi)
//            txtMessage.text = parsedBeacon.toString()
//            var distance = parsedBeacon.calculateDistance(parsedBeacon.txPower!!, parsedBeacon.rssi!!, 3.0)
//            txtMessage.setText(txtMessage.text.toString() + "\n distance:\n$distance")
//            var distanceAverageFilter = parsedBeacon.calculateDistanceAverageFilter(parsedBeacon.txPower!!, parsedBeacon.rssi!!, 3.0)
//            txtMessage.setText(txtMessage.text.toString() + "\n distanceFilter:\n$distanceAverageFilter")

        }
    }

    private val onBatchScanResultAction: (MutableList<ScanResult>?) -> Unit = {
        Log.d(TAG, "BatchScanResult: ${it.toString()}")
    }

    private val onScanFailedAction: (Int) -> Unit = {
        Log.d(TAG, "ScanFailed: $it")
    }

    // Clase interna que extiende Binder y define métodos públicos del servicio
    inner class MyBinder : Binder() {
        fun getService(): BeaconScannerService {
            return this@BeaconScannerService
        }
    }
}