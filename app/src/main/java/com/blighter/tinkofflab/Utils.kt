package com.blighter.tinkofflab

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.swiperefreshlayout.widget.CircularProgressDrawable

private lateinit var loadingDrawable: Drawable

fun composeDrawable(context: Context): Drawable {
    if (!::loadingDrawable.isInitialized) {
        val drawable = CircularProgressDrawable(context)
        drawable.centerRadius = 60f
        drawable.strokeWidth = 15f
        drawable.setColorSchemeColors(context.getColor(R.color.blue))
        drawable.start()
        loadingDrawable = drawable
    }
    return loadingDrawable
}