package com.akerimtay.rickandmorty.location.presentation.ui.list.adapter

import androidx.recyclerview.widget.DiffUtil
import com.akerimtay.rickandmorty.location.presentation.model.LocationItem

class LocationDiffCallback : DiffUtil.ItemCallback<LocationItem>() {

    override fun areItemsTheSame(oldItem: LocationItem, newItem: LocationItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: LocationItem, newItem: LocationItem): Boolean {
        return oldItem.id == newItem.id &&
            oldItem.name == newItem.name &&
            oldItem.imageResId == newItem.imageResId &&
            oldItem.type == newItem.type &&
            oldItem.dimension == newItem.dimension
    }

    override fun getChangePayload(oldItem: LocationItem, newItem: LocationItem): Any? {
        val diff = mutableSetOf<String>()
        if (oldItem.imageResId != newItem.imageResId) diff.add(DIFF_IMAGE)
        if (oldItem.name != newItem.name) diff.add(DIFF_NAME)
        if (oldItem.type != newItem.type) diff.add(DIFF_TYPE)
        if (oldItem.dimension != newItem.dimension) diff.add(DIFF_DIMENSION)
        return diff.takeIf { it.isNotEmpty() }
    }

    companion object {

        const val DIFF_IMAGE = "DIFF_IMAGE"
        const val DIFF_NAME = "DIFF_NAME"
        const val DIFF_TYPE = "DIFF_TYPE"
        const val DIFF_DIMENSION = "DIFF_DIMENSION"
    }
}