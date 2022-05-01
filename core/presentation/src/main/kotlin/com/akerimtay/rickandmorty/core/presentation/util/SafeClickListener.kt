package com.akerimtay.rickandmorty.core.presentation.util

import android.os.SystemClock
import android.view.View

class SafeClickListener(
    private val interval: Int = 1000,
    private val onSafeClick: (View) -> Unit,
) : View.OnClickListener {

    private var lastClickTimeMs: Long = 0L

    override fun onClick(v: View) {
        if (SystemClock.elapsedRealtime() - lastClickTimeMs < interval) {
            return
        }

        lastClickTimeMs = SystemClock.elapsedRealtime()
        onSafeClick(v)
    }
}