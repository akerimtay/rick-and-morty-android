package com.akerimtay.rickandmorty.core.presentation.util

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

fun Context.color(@ColorRes colorRes: Int): Int {
    return ContextCompat.getColor(this, colorRes)
}

fun View.color(@ColorRes colorRes: Int): Int {
    return ContextCompat.getColor(context, colorRes)
}

fun Context.colorStateList(@ColorRes colorRes: Int): ColorStateList {
    return ColorStateList.valueOf(color(colorRes))
}

fun Context.drawable(@DrawableRes drawableRes: Int): Drawable? {
    return ContextCompat.getDrawable(this, drawableRes)
}

val View.layoutInflater: LayoutInflater
    get() = LayoutInflater.from(context)

fun View.setOnSafeClickListener(listener: ((View) -> Unit)?) {
    if (listener == null) {
        setOnClickListener(null)
        return
    }

    setOnClickListener(SafeClickListener { v ->
        listener.invoke(v)
    })
}

fun View.setOnSafeClickListener(interval: Int, listener: (View) -> Unit) {
    setOnClickListener(SafeClickListener(interval) { v ->
        listener.invoke(v)
    })
}