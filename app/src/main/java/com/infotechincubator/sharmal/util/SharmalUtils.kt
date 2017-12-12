package com.infotechincubator.sharmal.util

import android.content.res.Resources
import android.os.Environment
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by kyawagwin on 9/12/17.
 */
object SharmalUtils {

    @Throws(IOException::class)
    fun createImageFile(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"

        val fileDir = File(Environment.getExternalStorageDirectory(), "/Pictures/Sharmal")
        if (!fileDir.exists())
            fileDir.mkdirs()

        val storageDir = Environment.getExternalStoragePublicDirectory("pictures/sharmal")

        return File.createTempFile(imageFileName, ".jpg", storageDir)
    }

    fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().getDisplayMetrics().density).toInt()
    }

    fun pxToDp(px: Int): Int {
        return (px / Resources.getSystem().getDisplayMetrics().density).toInt()
    }
}