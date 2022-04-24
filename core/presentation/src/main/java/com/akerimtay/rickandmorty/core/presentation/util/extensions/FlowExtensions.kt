package com.akerimtay.rickandmorty.core.presentation.util.extensions

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

fun <T> Flow<T>.launchWhenStarted(lifecycleOwner: LifecycleOwner) = with(lifecycleOwner) {
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            try {
                this@launchWhenStarted.collect()
            } catch (t: Throwable) {
            }
        }
    }
}

fun <T> Flow<T>.launchWhenStarted(lifecycleOwner: LifecycleOwner, action: suspend (value: T) -> Unit) =
    lifecycleOwner.lifecycleScope.launch {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            runCatching { this@launchWhenStarted.collectLatest(action) }
        }
    }