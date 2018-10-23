package com.example.alexandre.networkcom

import android.content.res.ColorStateList
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG="MainActivity"
    private var tcpClient:TcpClient = TcpClient("128.0.0.0",1001, applicationContext)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(TAG,"OnCreate Called")
        init()
    }


    fun init()
    {
        Log.d(TAG,"init function Called")

        if(!initIpWatcher() || !initPortWatcher()) buttonConnect.isEnabled=false
        else buttonConnect.isEnabled=true


        //Connection of Layout Object
        buttonConnect.setOnClickListener {
            //Start TCPClient
            TcpClient(txtIpaddress.text.toString(), txtPortNumber.text.toString().toInt(), applicationContext).execute()
        }


        Log.d(TAG, "init function done")
    }



    fun initIpWatcher() : Boolean
    {
        var result: Boolean = false
        txtIpaddress.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if(!tcpClient.checkIpAddress(txtIpaddress.text.toString())) {
                    txtIpaddress.setTextColor(ContextCompat.getColor(applicationContext, R.color.red))
                    result = false
                } else {
                    txtIpaddress.setTextColor(ContextCompat.getColor(applicationContext, R.color.black))
                    result = true
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
        return result
    }


    fun initPortWatcher() : Boolean
    {
        var result: Boolean = false
        txtPortNumber.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if(!tcpClient.checkPort(txtIpaddress.text.toString().toInt())) {
                    txtPortNumber.setTextColor(ContextCompat.getColor(applicationContext, R.color.red))
                    result = false
                } else {
                    txtPortNumber.setTextColor(ContextCompat.getColor(applicationContext, R.color.black))
                    result = true
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
        return result
    }


    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart Called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(TAG, "onRestart Called")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume Called")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop Called")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy Called")
    }
}
