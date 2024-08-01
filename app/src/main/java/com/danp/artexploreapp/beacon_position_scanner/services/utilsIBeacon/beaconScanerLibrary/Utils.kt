package com.danp.artexploreapp.beacon_position_scanner.services.utilsIBeacon.beaconScanerLibrary

object Utils {
    @OptIn(ExperimentalStdlibApi::class)
    fun toHexString(bytes:ByteArray):String{
        return bytes.toHexString()
    }
}