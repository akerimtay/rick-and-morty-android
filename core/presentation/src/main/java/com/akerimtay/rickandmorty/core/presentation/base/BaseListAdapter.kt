package com.akerimtay.rickandmorty.core.presentation.base

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

abstract class BaseListAdapter<T>(
    diffCallback: DiffUtil.ItemCallback<T>
) : ListAdapter<T, BaseViewHolder<T>>(diffCallback) {

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int, payloads: MutableList<Any>) {
        (payloads.firstOrNull() as? Set<*>)?.let { keys ->
            getItem(position)?.let {
                @Suppress("UNCHECKED_CAST")
                holder.update(it, keys as Set<String>)
            }
        } ?: onBindViewHolder(holder, position)
    }
}