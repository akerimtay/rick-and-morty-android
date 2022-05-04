package com.akerimtay.rickandmorty.core.common.usecase

import kotlinx.coroutines.flow.Flow

abstract class FlowUseCase<in P, R> {

    operator fun invoke(parameters: P): Flow<R> = execute(parameters)

    protected abstract fun execute(parameters: P): Flow<R>
}