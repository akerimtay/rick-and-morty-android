package com.akerimtay.rickandmorty.core.presentation.model

import android.content.Context
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes

interface TextResource {

    fun getString(context: Context): String

    companion object {

        fun create(string: String): TextResource = StringTextResource(string)

        fun create(@StringRes res: Int): TextResource = AndroidTextResource(res)

        fun create(
            @StringRes res: Int,
            vararg formatArgs: Any?
        ): TextResource = AndroidTextResource(res, formatArgs)
    }
}

private data class StringTextResource(val string: String) : TextResource {

    override fun getString(context: Context) = string
}

private class AndroidTextResource(
    @StringRes val resId: Int,
    val formatArgs: Array<out Any?> = emptyArray()
) : TextResource {

    override fun getString(context: Context): String {
        return if (formatArgs.isEmpty()) {
            context.getString(resId)
        } else {
            formatArgs.map { if (it is TextResource) it.getString(context) else it }.let {
                context.getString(resId, *it.toTypedArray())
            }
        }
    }
}

fun String.toResource(): TextResource = StringTextResource(this)

fun @receiver:StringRes Int.toResource(): TextResource = AndroidTextResource(this)
fun @receiver:StringRes Int.toResource(vararg formatArgs: Any?): TextResource = AndroidTextResource(this, formatArgs)

fun TextView.setText(textResource: TextResource) {
    text = textResource.getString(context)
}

fun Context.showToast(message: TextResource, duration: Int) {
    Toast.makeText(this, message.getString(this), duration).show()
}