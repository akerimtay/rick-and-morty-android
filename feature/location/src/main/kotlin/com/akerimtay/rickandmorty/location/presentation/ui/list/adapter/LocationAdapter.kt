package com.akerimtay.rickandmorty.location.presentation.ui.list.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.akerimtay.rickandmorty.core.presentation.base.BasePagingAdapter
import com.akerimtay.rickandmorty.core.presentation.base.BaseViewHolder
import com.akerimtay.rickandmorty.core.presentation.util.extensions.layoutInflater
import com.akerimtay.rickandmorty.core.presentation.util.extensions.setOnSafeClickListener
import com.akerimtay.rickandmorty.location.databinding.ItemLocationBinding
import com.akerimtay.rickandmorty.location.presentation.model.LocationItem
import com.bumptech.glide.Glide
import java.util.Locale

internal class LocationAdapter : BasePagingAdapter<LocationItem>(LocationDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<LocationItem> {
        return LocationViewHolder(ItemLocationBinding.inflate(parent.layoutInflater, parent, false))
    }

    internal class LocationDiffCallback : DiffUtil.ItemCallback<LocationItem>() {

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

    internal class LocationViewHolder(
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
                    LocationDiffCallback.DIFF_IMAGE -> updateImage(item)
                    LocationDiffCallback.DIFF_NAME -> updateName(item)
                    LocationDiffCallback.DIFF_TYPE -> updateType(item)
                    LocationDiffCallback.DIFF_DIMENSION -> updateDimension(item)
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
}