package com.example.alexandre.networkcom

import android.content.Context
import android.net.ConnectivityManager
import android.net.wifi.WifiManager


object NetworkUtil
{
    /* ---------------------------------
    WI-FI functions
    ------------------------------------ */
    fun isWifiConnected(pContext:Context):Boolean
    {
        return getConnectivityManager(pContext).activeNetworkInfo.type == ConnectivityManager.TYPE_WIFI
    }

    // ---------------------------------
    /*
    fun setWifi(pSet:Boolean, pContext: Context)
    {
        val wifiManager = pContext.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        wifiManager.isWifiEnabled=pSet;
    }*/


    /* ---------------------------------
    Network functions
    ------------------------------------ */
    fun isNetworkConnected(pContext: Context):Boolean
    {
        val lConnectivity = getConnectivityManager(pContext)
        return lConnectivity.activeNetworkInfo != null && lConnectivity.activeNetworkInfo.isConnected
    }


    /* ---------------------------------
    Getter
    ------------------------------------ */
    private fun getConnectivityManager(pContext: Context): ConnectivityManager
    {
        return pContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

}