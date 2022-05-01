@file:Suppress("unused")

package com.akerimtay.rickandmorty.core.presentation.util.extensions

inline fun <T> T.applyIf(applyCondition: Boolean, block: T.() -> Unit): T {
    if (applyCondition) block(this)
    return this
}