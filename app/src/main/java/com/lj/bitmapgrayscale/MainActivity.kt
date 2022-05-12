package com.lj.bitmapgrayscale

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lj.GrayScale

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var gray : Int = GrayScale.getMaxGrayScaleFromBitmap(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher))
        System.out.println("maxgray: $gray")

        var alpha = GrayScale.getAlphaFromBitmap(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher))
        System.out.println("alpha: $alpha")

    }
}