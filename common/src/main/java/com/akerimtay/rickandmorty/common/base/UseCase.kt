package com.akerimtay.rickandmorty.common.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class UseCase<in P, R> {
    protected abstract val dispatcher: CoroutineDispatcher

    suspend operator fun invoke(parameters: P): R = withContext(dispatcher) {
        execute(parameters)
    }

    protected abstract suspend fun execute(parameters: P): R
}