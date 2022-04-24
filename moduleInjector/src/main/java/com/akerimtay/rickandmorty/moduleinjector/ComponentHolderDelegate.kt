package com.akerimtay.rickandmorty.moduleinjector

import java.lang.ref.WeakReference

class ComponentHolderDelegate<A : BaseFeatureAPI, D : BaseFeatureDependencies, C : A>(
    private val componentFactory: (D) -> C
) : ComponentHolder<A, D> {

    override var dependencyProvider: (() -> D)? = null

    private var componentWeakRef: WeakReference<C>? = null

    fun getComponentImpl(): C {
        var component: C? = null

        synchronized(this) {
            dependencyProvider?.let { dependencyProvider ->
                component = componentWeakRef?.get()
                if (component == null) {
                    component = componentFactory(dependencyProvider())
                    componentWeakRef = WeakReference(component)
                }
            } ?: throw IllegalStateException("dependencyProvider is not initialized")
        }

        return component ?: throw IllegalStateException("Component holder is not initialized")
    }

    override fun get(): A = getComponentImpl()
}