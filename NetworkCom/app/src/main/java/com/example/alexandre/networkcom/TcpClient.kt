package com.example.alexandre.networkcom

import android.content.Context
import android.os.AsyncTask
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.Socket
import java.net.UnknownHostException
import android.util.Log
import java.net.InetAddress

/**
 * Created by Alexandre on 21/07/2018.
 */



class TcpClient(pIpAddress:String, pPort:Int, pContext: Context) : AsyncTask<Unit,Unit,String>()
{
    private val TAG="TcpClient"

    private val context = pContext
    private var ipAddress: InetAddress = InetAddress.getByAddress(pIpAddress.toByteArray())
    private var tcpPort: Int = pPort


    override fun doInBackground(vararg params: Unit?): String?
    {
        Log.i(TAG, "doInBackgound Called")

        /* --- Check Network and parameters */
        if(!NetworkUtil.isWifiConnected(context))
        {
            Log.e(TAG, "Wi-Fi is not connected !, try to connect it");
            return null
        }

        if(!checkIpAddress(ipAddress))
        {
            Log.e(TAG, "Ip Address is incorrect")
            return null
        }

        if(!checkPort(tcpPort))
        {
            Log.e(TAG, "Tcp Port is incorrect")
            return null
        }

        try {

            var socket = Socket(ipAddress, tcpPort)
            try {
                var incomingMessage = BufferedReader(InputStreamReader(socket.getInputStream()))
                Log.d(TAG, "I received '" + incomingMessage.toString() + "'")
            }
            catch(sendMessage: Exception)
            {
                Log.e(TAG, "error in socket : '$sendMessage'")
            }

        }
        catch (error: UnknownHostException)
        {
            Log.e(TAG, "error in ip address : '$error'")
        }
        catch (error: IOException)
        {
            Log.e(TAG, "error in I/O : '$error'")
        }
        catch (error: SecurityException)
        {
            Log.e(TAG, "error in security exception : '$error'")
        }
        catch (error: IllegalArgumentException)
        {
            Log.e(TAG, "error in port (0 to 65535 only) : '$error'")
        }
        return null
    }

    init
    {
        Log.d(TAG, "init Called")
        Log.v(TAG, "Ip Address : '" + ipAddress.toString() + "'")
        Log.v(TAG, "Tcp Port : '" + tcpPort.toString() + "'")
    }

    fun sendMessage(pMessage:String)
    {
        Log.d(TAG, "sendMessage called")
        Log.v(TAG, "Message to send : '$pMessage'")


    }




    /* ---------------------------------------------------------------------------------------------
    Check functions
    --------------------------------------------------------------------------------------------- */
    fun checkIpAddress(ipAddress:String) = if(ipAddress!=UnknownHostException) true else false
    fun checkPort(port:Int) = if(port>0 && port<=65535) true else false
}