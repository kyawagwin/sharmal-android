package com.infotechincubator.sharmal.extension

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import com.infotechincubator.sharmal.model.PermissionsRequestModel

/**
 * Created by kyawagwin on 12/12/17.
 */
fun Fragment.requestPermissionsExt(
        permissionsRequest: PermissionsRequestModel,
        callback: Unit
) {
    var allPermissionGranted = true

    // checking build version for permission request at runtime
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        permissionsRequest.permissions
                .filter { ContextCompat.checkSelfPermission(context!!, it) != PackageManager.PERMISSION_GRANTED }
                .forEach {
                    allPermissionGranted = false

                    ActivityCompat.requestPermissions(activity!!,
                            permissionsRequest.permissions,
                            permissionsRequest.requestCode)
                }

        if(allPermissionGranted) {
            callback
        }
    }
}