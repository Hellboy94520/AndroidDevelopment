package com.example.alexandre.networkcom

import android.content.Context
import android.os.AsyncTask
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.Socket
import java.net.UnknownHostException

/**
 * Created by Alexandre on 21/07/2018.
 */



class TcpClient(pIpAddress:String, pPort:Int, pContext: Context) : AsyncTask<Unit,Unit,String>()
{
    private val context = pContext
    private val connect = pContext.getSystemService(Context.CONNECTIVITY_SERVICE)
    private var className: String = "TcpClient"
    private var ipAddress: String = pIpAddress
    private var tcpPort: Int = pPort


    override fun doInBackground(vararg params: Unit?): String?
    {
        if(!NetworkUtil.isWifiConnected(context))
        {
            TraceUtil.error(className, "Wi-Fi is not connected !, try to connect it");
            return null;
        }

        try {
            var socket = Socket(ipAddress, tcpPort)
            try {
                var inMes = BufferedReader(InputStreamReader(socket.getInputStream()))
                TraceUtil.info(className, "I received '" + inMes.toString() + "'")
            }
            catch(sendMessage: Exception)
            {
                TraceUtil.error(className, "Error in socket : ", sendMessage)
            }
        }
        catch (e: UnknownHostException)
        {
            TraceUtil.error(className, "Error in ip address : ", e)
        }
        catch (e: IOException)
        {
            TraceUtil.error(className, "Error in I/O : ", e)
        }
        catch (e: SecurityException)
        {
            TraceUtil.error(className, "Error in securty exception : ", e)
        }
        catch (e: IllegalArgumentException)
        {
            TraceUtil.error(className, "Error in port (0 to 65535 only) : ", e)
        }
        return null;
    }

    init
    {
        TraceUtil.info(className, "init starting...")
        TraceUtil.info(className, "Ip Address : " + ipAddress)
        TraceUtil.info(className, "Tcp Port : " + tcpPort.toString())
        TraceUtil.info(className, "init started")
    }

    fun sendMessage(pMessage:String)
    {
        TraceUtil.info(className, "Message to send : '" + pMessage + "'")


    }


}