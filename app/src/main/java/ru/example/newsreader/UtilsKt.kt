package ru.example.newsreader

import android.content.Context
import android.net.ConnectivityManager

class UtilsKt {
    companion object {
        fun hasConnection(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val wifiInfo = cm.activeNetworkInfo

//        if (wifiInfo != null && wifiInfo.isConnected) {
//            return true
//        }
//        return false

            return wifiInfo != null && wifiInfo.isConnected
        }
    }
}