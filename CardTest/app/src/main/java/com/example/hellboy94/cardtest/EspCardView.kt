package com.example.hellboy94.cardtest

/**
 * Created by Hellboy94 on 28/02/2018.
 */


import android.content.Context
import android.graphics.Color
import android.support.v7.widget.CardView
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout


class EspCardView(mContext: Context) : CardView(mContext)
{
    var name:android.support.v7.widget.AppCompatAutoCompleteTextView = android.support.v7.widget.AppCompatAutoCompleteTextView(mContext)
    var address:android.support.v7.widget.AppCompatAutoCompleteTextView = android.support.v7.widget.AppCompatAutoCompleteTextView(mContext)
    var image:ImageView = ImageView(mContext)
    var buttonColor: Button = Button(mContext)
    var buttonLuminosity: Button = Button(mContext)

    private var _relativeLayout = RelativeLayout(mContext)
    private var _cardContentMarge: Int = Int.MAX_VALUE
    private var _nameTextSize: Float = Float.MAX_VALUE
    private var _smallTextSize: Float = Float.MAX_VALUE

    init
    {
        MYLOG.debug("EspCardView", "init", "enter")
        //préparation des variables
        _cardContentMarge = Conversion.getMargeSize(resources.getDimension(R.dimen.CardContentMarge))
        _nameTextSize = Conversion.getTextSize(resources.getDimension(R.dimen.NameTextCard))
        _smallTextSize = Conversion.getTextSize(resources.getDimension(R.dimen.SmallTextCard))
        MYLOG.debug("EspCardView", "init", "Variables OK")

        //préparation de la CardView
        cardElevation = 3.toFloat()
        id = View.generateViewId()
        MYLOG.debug("EspCardView", "init", "CardView parameters OK")

        //Ajout du RelativeLayout dans le CardView
        addView(_relativeLayout)
        MYLOG.debug("EspCardView", "init", "exit")
    }

    //----------------------
    fun createObject(pName:String, pAddresse:String, pId: Int)
    {
        MYLOG.debug("EspCardView", "createObject", "enter")
        createName(pName)
        createAddress(pAddresse)
        createImage(pId)
        createButtons()
        MYLOG.debug("EspCardView", "createObject", "exit")
    }

    //----------------------
    private fun createName(pName:String)
    {
        MYLOG.debug("EspCardView", "createName", "enter")
        // Configuration du LayoutParam
        var myParam = getObjectParam()
        myParam.addRule(RelativeLayout.ALIGN_PARENT_TOP)
        myParam.addRule(RelativeLayout.ALIGN_PARENT_LEFT)
        myParam.setMargins(_cardContentMarge,0,0,0)

        // Paramétrage du TextView
        name.setText(pName)
        name.id = View.generateViewId()

        name.setTextColor(Color.BLACK)
        name.textSize = _nameTextSize

        //Ajout dans la vue
        _relativeLayout.addView(name, myParam)
        MYLOG.debug("EspCardView", "createName", "exit")
    }

    //----------------------
    private fun createAddress(pAddresse:String)
    {
        MYLOG.debug("EspCardView", "createAddress", "enter")
        // Configuration du LayoutParam
        var myParam = getObjectParam()
        myParam.addRule(RelativeLayout.ALIGN_START, name.id)
        myParam.addRule(RelativeLayout.BELOW, name.id)
        myParam.setMargins(_cardContentMarge,0,0,0)

        // Paramétrage du TextView
        address.setText(pAddresse)
        address.id = View.generateViewId()
        address.setTextColor(Color.BLACK)
        address.textSize = _smallTextSize

        //Ajout dans la vue
        _relativeLayout.addView(address, myParam)
        MYLOG.debug("EspCardView", "createAddress", "exit")
    }

    //----------------------
    private fun createImage(pId:Int)
    {
        MYLOG.debug("EspCardView", "createImage", "enter")
        // Configuration du LayoutParam
        val myParams = RelativeLayout.LayoutParams(266,260)
        myParams.setMargins(0,0,_cardContentMarge,0)
        myParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
        myParams.addRule(RelativeLayout.ALIGN_PARENT_TOP)

        // Paramétrage de l'image
        image.setImageResource(pId)
        image.id = View.generateViewId()

        //Ajout dans la vue
        _relativeLayout.addView(image, myParams)
        MYLOG.debug("EspCardView", "createImage", "exit")
    }

    //----------------------
    private fun createButtons()
    {
        MYLOG.debug("EspCardView", "createButtons", "enter")
        /* Bouton de la couleur */
        // Configuration du LayoutParam
        var myColorParam = getObjectParam()
        myColorParam.addRule(RelativeLayout.ALIGN_START, name.id)
        myColorParam.addRule(RelativeLayout.BELOW, address.id)
        myColorParam.setMargins(_cardContentMarge,0,0,0)

        // Paramétrage du bouton
        buttonColor.id = View.generateViewId()
        buttonColor.text = resources.getText(R.string.ColorButtonText)
        //buttonColor.setBackgroundColor(Color.GRAY)
        buttonColor.textSize = _smallTextSize

        //Ajout dans la vue
        _relativeLayout.addView(buttonColor, myColorParam)
        MYLOG.debug("EspCardView", "createButtons", "first button OK")


        /* Bouton de la luminosité */
        // Configuration du LayoutParam
        var myLumParam = getObjectParam()
        myLumParam.addRule(RelativeLayout.BELOW, address.id)
        myLumParam.addRule(RelativeLayout.RIGHT_OF, buttonColor.id)
        myLumParam.setMargins(_cardContentMarge,0,0,0)

        // Paramétrage du bouton
        buttonLuminosity.id = View.generateViewId()
        buttonLuminosity.text = resources.getText(R.string.LuminosityButtonText)
        //buttonLuminosity.setBackgroundColor(Color.WHITE.toInt())
        buttonLuminosity.textSize = _smallTextSize
        _relativeLayout.addView(buttonLuminosity, myLumParam)
        MYLOG.debug("EspCardView", "createButtons", "exit")
    }

    //----------------------
    private fun getObjectParam(): RelativeLayout.LayoutParams
    {
        var lObjectParam = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT)
        return lObjectParam
    }

}
