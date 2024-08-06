package com.pyloa.fastnews

import android.content.res.Resources
import android.os.Build
import android.util.Log
import kotlin.math.round

actual class Platform {
    actual val osName: String
        get() = "Android" // Build.VERSION.BASE_OS
    actual val osVersion: String
        get() = Build.VERSION.SDK_INT.toString()
    actual val deviceModel: String
        get() = "${Build.MANUFACTURER} ${Build.MODEL}"
    actual val density: Int
        get() = round(Resources.getSystem().displayMetrics.density).toInt()

    actual fun logInfo() {
        Log.d(
            "FastNews",
            "($osName, $osVersion, $deviceModel, $density)"
        )
    }
}