package com.idnp2024a.beaconscanner.permissions

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class BTPermissions(private val activity: AppCompatActivity){
    private val TAG = "BTPermissions"
    private lateinit var permissionsList: ArrayList<String>
    private var permissionsCount = 0
    private lateinit var alertDialog: AlertDialog
    private var dialogShown = false

    val permissions = arrayOf(
        android.Manifest.permission.ACCESS_COARSE_LOCATION,
        android.Manifest.permission.ACCESS_FINE_LOCATION,
    )

    fun check() {
        permissionsList = ArrayList<String>()
        permissions.forEach {
            permissionsList.add(it)
        }
        askForPermissions(permissionsList)
    }

    var permissionsLauncher: ActivityResultLauncher<Array<String>> =
        activity.registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            val list = ArrayList(it.values)
            permissionsList = ArrayList<String>()
            permissionsCount = 0

            for (i in list.indices) {
                if (activity.shouldShowRequestPermissionRationale(permissions.get(i))) {
                    permissionsList.add(permissions.get(i))
                } else if (!hasPermission(activity, permissions.get(i))) {
                    permissionsCount++
                }
            }

            if (permissionsList.size > 0) {
                //Some permissions are denied and can be asked again.
                askForPermissions(permissionsList)
            } else if (permissionsCount > 0) {
                //Show alert dialog
                showPermissionDialog()
            } else {
                //All permissions granted. Do your stuff 🤞
                Log.d(TAG, "All permissions are granted!")
            }
        }
    private fun hasPermission(context: Context, permissionStr: String): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            permissionStr
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun askForPermissions(permissionsList: ArrayList<String>) {
        val newPermissionStr = ArrayList<String>()

        permissionsList.forEach {
            newPermissionStr.add(it)
        }

        Log.d(TAG, newPermissionStr.size.toString() + "--")
        if (newPermissionStr.size > 0) {
            permissionsLauncher.launch(newPermissionStr.toTypedArray())
        } else {
            /* User has pressed 'Deny & Don't ask again' so we have to show the enable permissions dialog
        which will lead them to app details page to enable permissions from there. */
            showPermissionDialog()
        }
    }


    private fun showPermissionDialog() {
        if (!dialogShown) {
            val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
            builder.setTitle("Permission required")
                .setMessage("Some permissions are need to be allowed to use this app without any problems.")
                .setPositiveButton("Settings") { dialog, which ->
                    dialog.dismiss()
                }

            alertDialog = builder.create()
            if (!alertDialog.isShowing) {
                alertDialog.show()
                dialogShown = true
            }
        }
    }
}