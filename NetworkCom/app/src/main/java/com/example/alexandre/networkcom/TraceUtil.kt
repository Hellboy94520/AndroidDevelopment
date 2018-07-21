package com.example.alexandre.networkcom

/**
 * Created by Alexandre on 21/07/2018.
 */

import android.util.Log

object TraceUtil
{
    fun info(pClasse:String, pMes: String)
    {
        Log.d("INFO - "+ pClasse, pMes)
    }

    fun error(pClasse:String, pMes: String, pException:Exception = Exception())
    {
        Log.e("ERROR - "+pClasse, pMes, pException)
    }
 }