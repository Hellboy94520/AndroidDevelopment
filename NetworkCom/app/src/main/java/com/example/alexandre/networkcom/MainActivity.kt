package com.example.alexandre.networkcom

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private var className: String = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }



    fun init()
    {
        TraceUtil.info(className, "init startint...")

        //Start TCP/IP Client
        TcpClient("192.168.1.49", 9999, applicationContext).execute()

        TraceUtil.info(className, "init started")
    }
}
