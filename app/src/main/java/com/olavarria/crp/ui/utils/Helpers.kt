package com.olavarria.crp.ui.utils

import android.content.Context
import android.os.Build
import android.provider.Settings
import android.telephony.TelephonyManager
import android.widget.EditText
import android.widget.TextView
import androidx.viewbinding.ViewBinding
import java.util.*
import kotlin.collections.HashMap

fun getDeviceId(context: Context): String {
    val tm = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
    //val tmDevice = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) tm.getDeviceId(0) else tm.deviceId
    //val tmSerial = tm.simSerialNumber
    val androidId =
        Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID)

    //return UUID(androidId.hashCode().toLong(), tmDevice.hashCode().toLong() shl 32 or tmSerial.hashCode().toLong()).toString()
    return androidId
}

fun setFilterConfig(search: String): HashMap<String, String> {
    var filterMaps = HashMap<String, String>()
    var mSearch = "$search&"
    if (mSearch.contains("?")){
        mSearch = mSearch.replace("?","")
        val list = mSearch.split("&")
        list.forEach {
            val list2 = it.split("=")
            if (list2.last().isNotEmpty())
                filterMaps[list2.first()] = list2.last()
        }
    }
    return filterMaps
}

