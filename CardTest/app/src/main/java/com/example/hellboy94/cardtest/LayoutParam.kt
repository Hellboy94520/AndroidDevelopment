package com.example.hellboy94.cardtest

import android.view.View
import android.widget.RelativeLayout

/**
 * Created by Hellboy94 on 02/03/2018.
 */


object LayoutParam
{
    fun getCard(pMarge:Int): RelativeLayout.LayoutParams
    {
        var myParam = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
        myParam.setMargins(pMarge,12,12,12)
        return myParam
    }

    fun getCardBelow(pView:View, pMarge: Int) : RelativeLayout.LayoutParams
    {
        var myParam = getCard(pMarge)
        myParam.addRule(RelativeLayout.BELOW, pView.id)
        return myParam
    }


}