package com.akerimtay.rickandmorty.core.presentation.base

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView

@SuppressLint("NotifyDataSetChanged")
abstract class BaseAdapter<T> : RecyclerView.Adapter<BaseViewHolder<T>>() {

    protected val data = mutableListOf<T>()

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) = holder.onBind(data[position])

    override fun onViewRecycled(holder: BaseViewHolder<T>) {
        super.onViewRecycled(holder)
        holder.onViewRecycled()
    }

    open fun getItem(position: Int): T = data[position]

    fun clear() {
        data.clear()
        notifyDataSetChanged()
    }

    open fun setItems(items: List<T>) {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }
}