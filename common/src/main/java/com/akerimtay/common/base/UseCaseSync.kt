package com.akerimtay.common.base

abstract class UseCaseSync<in P, R> {
    operator fun invoke(parameters: P): R = execute(parameters)

    protected abstract fun execute(parameters: P): R
}