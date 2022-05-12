package com.lj

import android.graphics.Bitmap

object GrayScale {
    fun getMaxGrayScaleFromBitmap(bitmap: Bitmap): Int {
        val width: Int = bitmap.width
        val height: Int = bitmap.height
        val allpixel = width * height
        val pixels = IntArray(allpixel)
        val grayscale = IntArray(allpixel)
        val histogram = IntArray(256)
        var maxGrayScale = 0
        val alpha = 0.0f
        val range = allpixel / 20
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height)

        var i: Int
        run {
            i = 0
            while (i < allpixel) {
                val p = pixels[i]
                val r = p and 16711680 shr 16
                val g = p and '\uff00'.code shr 8
                val b = p and 255
                grayscale[i] =
                    (r.toDouble() * 0.299 + g.toDouble() * 0.587 + b.toDouble() * 0.114).toInt()
                ++histogram[grayscale[i]]
                maxGrayScale = grayscale[i].coerceAtLeast(maxGrayScale)
                ++i
            }
        }

        if (histogram[255] >= range) {
            maxGrayScale = 255
        } else {
            i = 254
            while (i >= 0) {
                if (histogram[i] + histogram[i + 1] >= range) {
                    maxGrayScale = i
                    break
                }
                histogram[i] += histogram[i + 1]
                --i
            }
        }
        return maxGrayScale
    }

    fun getAlphaFromBitmap(bitmap: Bitmap): Float {
        var maxGrayScale = getMaxGrayScaleFromBitmap(bitmap)
        return if (maxGrayScale >= 100) {
            100.0f / maxGrayScale.toFloat()
        } else {
            1.0f
        }
    }
}
