package com.example.hellboy94.cardtest

/**
 * Created by Hellboy94 on 28/02/2018.
 */
import android.util.Log

/**
 * Created by Hellboy94 on 28/02/2018.
 */
object MYLOG
{

    fun debug(pClasse: String, pFunction: String, pMes: String)
    {
        Log.d(constructHeader(pClasse,pFunction),pMes)
    }

    fun validData(pClasse:String, pFunction:String, pName:String):String
    {
        val lMes = "OK"
        Log.d(constructHeader(pClasse,pFunction),"$pName Validation $lMes")
        return lMes
    }

    fun errorForEspObject(pClasse:String, pFunction:String, pName:String, pValue:String, pPosition:String):String
    {
        val lMes = "Incorrect $pName value \"$pValue\", at \"$pPosition\""
        Log.e(constructHeader(pClasse,pFunction), lMes)
        return "ERROR " + lMes
    }


    fun error(pClasse:String, pFunction:String, pMes:String):String
    {
        Log.e(constructHeader(pClasse,pFunction), pMes)
        return "ERROR " + pMes
    }

    private fun constructHeader(pClasse:String, pFunction:String):String
    {
        return "$pClasse - $pFunction"
    }
}