package com.infotechincubator.sharmal.extension

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import com.infotechincubator.sharmal.model.PermissionsRequestModel

/**
 * Created by kyawagwin on 9/12/17.
 */
fun Activity.requestPermissionsExt(
        permissionsRequest: PermissionsRequestModel,
        callback: Unit
) {
    var allPermissionGranted = true

    // checking build version for permission request at runtime
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        permissionsRequest.permissions
                .filter { ContextCompat.checkSelfPermission(baseContext, it) != PackageManager.PERMISSION_GRANTED }
                .forEach {
                    allPermissionGranted = false

                    ActivityCompat.requestPermissions(this,
                            permissionsRequest.permissions,
                            permissionsRequest.requestCode)
                }

        if(allPermissionGranted) {
            callback
        }
    }
}