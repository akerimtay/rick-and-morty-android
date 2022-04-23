package com.akerimtay.rickandmorty.core.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class UseCase<in P, R> {

    open val dispatcher: CoroutineDispatcher = Dispatchers.IO

    suspend operator fun invoke(parameters: P): R = withContext(dispatcher) {
        execute(parameters)
    }

    protected abstract suspend fun execute(parameters: P): R
}

object NoParams