package com.example.hellboy94.cardtest

/**
 * Created by Hellboy94 on 02/03/2018.
 */


object Conversion
{
    var _scaleScreen: Float = Float.MIN_VALUE

    fun getTextSize(pDimPixSize:Float) : Float
    {
        return (pDimPixSize/ _scaleScreen)
    }

    fun getMargeSize(pDimPixSize: Float) : Int
    {
        return getTextSize(pDimPixSize).toInt()
    }

}