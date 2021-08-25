@file:Suppress("unused")

package com.akerimtay.common.util

/**
 * Helper to force a when statement to assert all options are matched in a when statement.
 */
val <T> T.checkAllMatched: T
    get() = this

inline fun <T> T.applyIf(applyCondition: Boolean, block: T.() -> Unit): T {
    if (applyCondition) block(this)
    return this
}

inline fun <T> Iterable<T>.filterIf(filterCondition: Boolean, predicate: (T) -> Boolean): List<T> {
    return if (filterCondition) filterTo(ArrayList(), predicate) else toList()
}