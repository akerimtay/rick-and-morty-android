package com.akerimtay.rickandmorty.moduleinjector

interface BaseDependencyHolder<D : BaseFeatureDependencies> {
    val dependencies: D
}

abstract class DependencyHolder<D : BaseFeatureDependencies> : BaseDependencyHolder<D> {
    abstract val block: (BaseDependencyHolder<D>) -> D

    override val dependencies: D
        get() = block(this)
}

abstract class DependencyHolder1<A1 : BaseFeatureAPI, D : BaseFeatureDependencies>(
    private val api1: A1,
) : BaseDependencyHolder<D> {
    abstract val block: (BaseDependencyHolder<D>, A1) -> D

    override val dependencies: D
        get() = block(this, api1)
}

abstract class DependencyHolder2<A1 : BaseFeatureAPI, A2 : BaseFeatureAPI, D : BaseFeatureDependencies>(
    private val api1: A1,
    private val api2: A2,
) : BaseDependencyHolder<D> {
    abstract val block: (BaseDependencyHolder<D>, A1, A2) -> D

    override val dependencies: D
        get() = block(this, api1, api2)
}