package com.akerimtay.rickandmorty.common.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlin.properties.Delegates

class ContentAdapter<T : ContentType>(
    private val typeSet: Array<T>
) : RecyclerView.Adapter<BaseHolder<T, BaseContentItem<T>>>() {
    private val viewPool: RecyclerView.RecycledViewPool = RecyclerView.RecycledViewPool()
    var afterDispatchList: () -> Unit = {}
    var collection: List<BaseContentItem<T>> by Delegates.observable(emptyList()) { _, old, new ->
        DiffUtil.calculateDiff(BaseContentItem.Diff(old, new)).dispatchUpdatesTo(this@ContentAdapter)
        afterDispatchList()
    }

    override fun getItemCount() = collection.size

    override fun getItemViewType(position: Int) = collection[position].type.type()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        typeSet[viewType].let {
            LayoutInflater.from(parent.context).inflate(it.getLayout(), parent, false).let { view ->
                (it.createHolder(view) as BaseHolder<T, BaseContentItem<T>>).apply {
                    getNestedRecyclerView()?.setRecycledViewPool(viewPool)
                }
            }
        }

    override fun onBindViewHolder(
        holder: BaseHolder<T, BaseContentItem<T>>,
        position: Int,
        payloads: MutableList<Any>
    ) = (payloads.firstOrNull()).let { status ->
        if (status == null || status !is Set<*>) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            holder.updateHolder(collection[position], status as Set<String>)
        }
    }

    override fun onBindViewHolder(holder: BaseHolder<T, BaseContentItem<T>>, position: Int) {
        holder.bind(collection[position])
    }

    override fun onViewRecycled(holder: BaseHolder<T, BaseContentItem<T>>) =
        holder.onViewRecycled()

    override fun onViewAttachedToWindow(holder: BaseHolder<T, BaseContentItem<T>>) =
        holder.onViewAttachedToWindow()

    override fun onViewDetachedFromWindow(holder: BaseHolder<T, BaseContentItem<T>>) =
        holder.onViewDetachedFromWindow()
}