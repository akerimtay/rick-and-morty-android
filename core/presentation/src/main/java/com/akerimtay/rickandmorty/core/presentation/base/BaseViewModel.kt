package com.akerimtay.rickandmorty.core.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel(), CoroutineScope {
    override val coroutineContext: CoroutineContext = viewModelScope.coroutineContext

    protected fun CoroutineScope.launchSafe(
        start: (() -> Unit)? = null,
        body: suspend () -> Unit,
        handleError: (Throwable) -> Unit,
        finish: (() -> Unit)? = null
    ): Job = launch {
        try {
            start?.invoke()
            body.invoke()
        } catch (e: Throwable) {
            handleError(e)
        } finally {
            finish?.invoke()
        }
    }
}

interface Action