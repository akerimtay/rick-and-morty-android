package com.akerimtay.rickandmorty.common.adapter

import androidx.recyclerview.widget.DiffUtil

abstract class BaseContentItem<T : ContentType>(open var id: String) {
    abstract val type: T

    open fun areItemsTheSame(other: BaseContentItem<T>) = id == other.id && type == other.type
    open fun areContentsTheSame(other: BaseContentItem<T>) = false
    open fun getChangePayload(other: BaseContentItem<T>, keys: MutableSet<String>) {}
    fun getChangePayload(other: BaseContentItem<T>) = mutableSetOf<String>()
        .apply { getChangePayload(other, this) }
        .takeIf { it.isNotEmpty() }

    class Diff<T : ContentType>(
        private val oldList: List<BaseContentItem<T>>,
        private val newList: List<BaseContentItem<T>>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldPos: Int, newPos: Int): Boolean =
            oldList[oldPos].areItemsTheSame(newList[newPos])

        override fun areContentsTheSame(oldPos: Int, newPos: Int): Boolean =
            oldList[oldPos].areContentsTheSame(newList[newPos])

        override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition].getChangePayload(newList[newItemPosition])
    }

    class DiffItem<T : ContentType> : DiffUtil.ItemCallback<BaseContentItem<T>>() {
        override fun areItemsTheSame(oldItem: BaseContentItem<T>, newItem: BaseContentItem<T>) =
            oldItem.areItemsTheSame(newItem)

        override fun areContentsTheSame(oldItem: BaseContentItem<T>, newItem: BaseContentItem<T>) =
            oldItem.areContentsTheSame(newItem)

        override fun getChangePayload(oldItem: BaseContentItem<T>, newItem: BaseContentItem<T>) =
            oldItem.getChangePayload(newItem)
    }
}