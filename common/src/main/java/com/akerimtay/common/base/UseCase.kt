package com.akerimtay.common.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class UseCase<in P, R> {
    protected abstract val dispatcher: CoroutineDispatcher

    suspend operator fun invoke(parameters: P): R = withContext(dispatcher) {
        execute(parameters)
    }

    protected abstract suspend fun execute(parameters: P): R

    suspend fun invokeInCurrentContext(parameters: P): R = execute(parameters)
}