package com.akerimtay.rickandmorty.location.presentation.list.adapter

import com.akerimtay.rickandmorty.core.presentation.base.BaseViewHolder
import com.akerimtay.rickandmorty.core.presentation.util.extensions.setOnSafeClickListener
import com.akerimtay.rickandmorty.location.databinding.ItemLocationBinding
import com.akerimtay.rickandmorty.location.presentation.list.adapter.LocationDiffCallback.Companion.DIFF_DIMENSION
import com.akerimtay.rickandmorty.location.presentation.list.adapter.LocationDiffCallback.Companion.DIFF_IMAGE
import com.akerimtay.rickandmorty.location.presentation.list.adapter.LocationDiffCallback.Companion.DIFF_NAME
import com.akerimtay.rickandmorty.location.presentation.list.adapter.LocationDiffCallback.Companion.DIFF_TYPE
import com.akerimtay.rickandmorty.location.presentation.model.LocationItem
import com.bumptech.glide.Glide
import java.util.Locale

class LocationViewHolder(
    private val viewBinding: ItemLocationBinding,
) : BaseViewHolder<LocationItem>(viewBinding.root) {

    override fun onBind(item: LocationItem): Unit = with(viewBinding) {
        updateImage(item)
        updateName(item)
        updateType(item)
        updateDimension(item)

        root.setOnSafeClickListener { item.onItemClickListener() }
    }

    override fun update(item: LocationItem, keys: Set<String>) {
        keys.forEach { key ->
            when (key) {
                DIFF_IMAGE -> updateImage(item)
                DIFF_NAME -> updateName(item)
                DIFF_TYPE -> updateType(item)
                DIFF_DIMENSION -> updateDimension(item)
            }
        }
    }

    private fun updateImage(item: LocationItem): Unit = with(viewBinding) {
        Glide.with(root)
            .load(item.imageResId)
            .into(ivLocation)
    }

    private fun updateName(item: LocationItem): Unit = with(viewBinding) {
        tvName.text = item.name
    }

    private fun updateType(item: LocationItem): Unit = with(viewBinding) {
        tvType.text = item.type
    }

    private fun updateDimension(item: LocationItem): Unit = with(viewBinding) {
        tvDimension.text = item.dimension.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
        }
    }
}