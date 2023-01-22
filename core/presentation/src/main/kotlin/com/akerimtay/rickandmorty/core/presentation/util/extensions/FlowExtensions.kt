package com.akerimtay.rickandmorty.core.presentation.util.extensions

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
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

inline fun <T : Any, R : Any> Flow<PagingData<T>>.mapTo(
    crossinline transform: suspend (value: T) -> R
): Flow<PagingData<R>> = map { list -> list.map { transform(it) } }