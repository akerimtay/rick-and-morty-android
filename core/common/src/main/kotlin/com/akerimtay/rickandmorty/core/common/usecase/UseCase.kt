package com.akerimtay.rickandmorty.core.common.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class UseCase<in P, R> {

    open val dispatcher: CoroutineDispatcher = Dispatchers.Default

    suspend operator fun invoke(parameters: P): R = withContext(dispatcher) {
        execute(parameters)
    }

    protected abstract suspend fun execute(parameters: P): R
}