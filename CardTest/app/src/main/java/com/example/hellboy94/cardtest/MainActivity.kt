package com.example.hellboy94.cardtest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.RelativeLayout


class MainActivity : AppCompatActivity() {

    private var _cardContentMarge: Int = Int.MAX_VALUE


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        demo()
    }


    fun demo()
    {
        var myLayout: RelativeLayout = findViewById(R.id.mainLayout)


        var myCardTest = EspCardView(applicationContext)
        myCardTest.createObject("Essaie", "128.0.0.1", R.drawable.couch)
        myLayout.addView(myCardTest, LayoutParam.getCard(_cardContentMarge))

        val myCardTest2 = EspCardView(applicationContext)
        myCardTest2.createObject("Essaie n02", "128.0.0.2", R.drawable.couch)
        myLayout.addView(myCardTest2, LayoutParam.getCardBelow(myCardTest, _cardContentMarge))

        val myCardTest3 = EspCardView(applicationContext)
        myCardTest3.createObject("Essaie n03", "128.0.0.3", R.drawable.couch)
        myLayout.addView(myCardTest3, LayoutParam.getCardBelow(myCardTest2, _cardContentMarge))

    }


    fun init()
    {
        MYLOG.debug("MainActivity", "init", "enter in")
        Conversion._scaleScreen = resources.displayMetrics.scaledDensity
        _cardContentMarge = Conversion.getMargeSize(resources.getDimension(R.dimen.CardMarge))
        MYLOG.debug("MainActivity", "init", "exit")
    }

}
