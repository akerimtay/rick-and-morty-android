package com.akerimtay.rickandmorty.core.common.usecase

abstract class FlowUseCase<in P, R> {

    operator fun invoke(parameters: P): R = execute(parameters)

    protected abstract fun execute(parameters: P): R
}