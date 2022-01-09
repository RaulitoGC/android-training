package com.rguzmanc.android_lifecycle.system_restoration

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


fun AppCompatActivity.checkAndRequestPermissions(requestCode: Int): Boolean {
    val storagePermission = ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
    val cameraPermission = ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.CAMERA
    )
    val listPermissionsNeeded: MutableList<String> = ArrayList()
    if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
        listPermissionsNeeded.add(Manifest.permission.CAMERA)
    }
    if (storagePermission != PackageManager.PERMISSION_GRANTED) {
        listPermissionsNeeded
            .add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }
    if (listPermissionsNeeded.isNotEmpty()) {
        ActivityCompat.requestPermissions(this, listPermissionsNeeded.toTypedArray(), requestCode)
        return false
    }
    return true
}
