package com.akerimtay.rickandmorty.characters.presentation.ui.list.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.akerimtay.rickandmorty.characters.R
import com.akerimtay.rickandmorty.characters.databinding.ItemCharacterGridBinding
import com.akerimtay.rickandmorty.characters.databinding.ItemCharacterHeaderBinding
import com.akerimtay.rickandmorty.characters.databinding.ItemCharacterHorizontalBinding
import com.akerimtay.rickandmorty.characters.presentation.model.CharacterItem
import com.akerimtay.rickandmorty.characters.presentation.model.ListViewType
import com.akerimtay.rickandmorty.core.presentation.base.BasePagingAdapter
import com.akerimtay.rickandmorty.core.presentation.base.BaseViewHolder
import com.akerimtay.rickandmorty.core.presentation.util.extensions.color
import com.akerimtay.rickandmorty.core.presentation.util.extensions.layoutInflater
import com.akerimtay.rickandmorty.core.presentation.util.extensions.setOnSafeClickListener
import com.bumptech.glide.Glide

internal class CharacterAdapter : BasePagingAdapter<CharacterItem>(CharacterDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<CharacterItem> {
        return when (viewType) {
            VIEW_HEADER -> CharacterHeaderViewHolder(
                ItemCharacterHeaderBinding.inflate(parent.layoutInflater, parent, false)
            )
            VIEW_HORIZONTAL -> CharacterHorizontalViewHolder(
                ItemCharacterHorizontalBinding.inflate(parent.layoutInflater, parent, false)
            )
            VIEW_GRID -> CharacterGridViewHolder(
                ItemCharacterGridBinding.inflate(parent.layoutInflater, parent, false)
            )
            else -> throw IllegalStateException("$viewType is not supported")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is CharacterItem.Header -> VIEW_HEADER
            is CharacterItem.HorizontalItem -> VIEW_HORIZONTAL
            is CharacterItem.GridItem -> VIEW_GRID
            else -> super.getItemViewType(position)
        }
    }

    internal class CharacterDiffCallback : DiffUtil.ItemCallback<CharacterItem>() {

        override fun areItemsTheSame(oldItem: CharacterItem, newItem: CharacterItem): Boolean {
            return when {
                oldItem is CharacterItem.Header && newItem is CharacterItem.Header -> {
                    oldItem.characterCount == newItem.characterCount
                }
                oldItem is CharacterItem.HorizontalItem && newItem is CharacterItem.HorizontalItem -> {
                    oldItem.name == newItem.name
                }
                oldItem is CharacterItem.GridItem && newItem is CharacterItem.GridItem -> {
                    oldItem.name == newItem.name
                }
                else -> false
            }
        }

        override fun areContentsTheSame(oldItem: CharacterItem, newItem: CharacterItem): Boolean {
            return when {
                oldItem is CharacterItem.Header && newItem is CharacterItem.Header -> {
                    oldItem.characterCount == newItem.characterCount &&
                        oldItem.viewType == newItem.viewType
                }
                oldItem is CharacterItem.HorizontalItem && newItem is CharacterItem.HorizontalItem -> {
                    oldItem.name == newItem.name &&
                        oldItem.imageUrl == newItem.imageUrl &&
                        oldItem.statusNameResId == newItem.statusNameResId &&
                        oldItem.statusColorResId == newItem.statusColorResId &&
                        oldItem.species == newItem.species
                }
                oldItem is CharacterItem.GridItem && newItem is CharacterItem.GridItem -> {
                    oldItem.name == newItem.name &&
                        oldItem.imageUrl == newItem.imageUrl &&
                        oldItem.statusNameResId == newItem.statusNameResId &&
                        oldItem.statusColorResId == newItem.statusColorResId &&
                        oldItem.species == newItem.species
                }
                else -> false
            }
        }

        override fun getChangePayload(oldItem: CharacterItem, newItem: CharacterItem): Any? {
            val diff = mutableSetOf<String>()
            when {
                oldItem is CharacterItem.Header && newItem is CharacterItem.Header -> {
                    if (oldItem.characterCount != newItem.characterCount) diff.add(DIFF_CHARACTER_COUNT)
                    if (oldItem.viewType != newItem.viewType) diff.add(DIFF_VIEW_TYPE)
                }
                oldItem is CharacterItem.HorizontalItem && newItem is CharacterItem.HorizontalItem -> {
                    if (oldItem.name != newItem.name) diff.add(DIFF_NAME)
                    if (oldItem.imageUrl != newItem.imageUrl) diff.add(DIFF_IMAGE_URL)
                    if (oldItem.statusNameResId != newItem.statusNameResId ||
                        oldItem.statusColorResId != newItem.statusColorResId
                    ) diff.add(DIFF_STATUS)
                    if (oldItem.species != newItem.species) diff.add(DIFF_SPECIES)
                }
                oldItem is CharacterItem.GridItem && newItem is CharacterItem.GridItem -> {
                    if (oldItem.name != newItem.name) diff.add(DIFF_NAME)
                    if (oldItem.imageUrl != newItem.imageUrl) diff.add(DIFF_IMAGE_URL)
                    if (oldItem.statusNameResId != newItem.statusNameResId ||
                        oldItem.statusColorResId != newItem.statusColorResId
                    ) diff.add(DIFF_STATUS)
                    if (oldItem.species != newItem.species) diff.add(DIFF_SPECIES)
                }
            }
            return diff.takeIf { it.isNotEmpty() }
        }

        companion object {

            const val DIFF_NAME = "DIFF_NAME"
            const val DIFF_IMAGE_URL = "DIFF_IMAGE_URL"
            const val DIFF_STATUS = "DIFF_STATUS"
            const val DIFF_SPECIES = "DIFF_SPECIES"

            const val DIFF_CHARACTER_COUNT = "DIFF_CHARACTER_COUNT"
            const val DIFF_VIEW_TYPE = "DIFF_VIEW_TYPE"
        }
    }

    internal class CharacterHeaderViewHolder(
        private val viewBinding: ItemCharacterHeaderBinding
    ) : BaseViewHolder<CharacterItem>(viewBinding.root) {

        override fun onBind(item: CharacterItem): Unit = with(viewBinding) {
            item as CharacterItem.Header

            updateCount(item)
            updateViewType(item)

            ivSelectorViewType.setOnSafeClickListener { item.onChangeViewTypeListener() }
        }

        override fun update(item: CharacterItem, keys: Set<String>) {
            item as CharacterItem.Header

            keys.forEach {
                when (it) {
                    CharacterDiffCallback.DIFF_CHARACTER_COUNT -> updateCount(item)
                    CharacterDiffCallback.DIFF_VIEW_TYPE -> updateViewType(item)
                }
            }
        }

        private fun updateCount(item: CharacterItem.Header): Unit = with(viewBinding) {
            tvCharacterCount.text = itemView.context.getString(R.string.characters_count_format, item.characterCount)
        }

        private fun updateViewType(item: CharacterItem.Header): Unit = with(viewBinding) {
            ivSelectorViewType.setImageResource(
                if (item.viewType == ListViewType.HORIZONTAL) R.drawable.ic_bullet_24 else R.drawable.ic_grid_24
            )
        }
    }

    internal class CharacterHorizontalViewHolder(
        private val viewBinding: ItemCharacterHorizontalBinding,
    ) : BaseViewHolder<CharacterItem>(viewBinding.root) {

        override fun onBind(item: CharacterItem): Unit = with(viewBinding) {
            item as CharacterItem.HorizontalItem

            updateAvatar(item)
            updateStatus(item)
            updateName(item)
            updateSpecies(item)

            root.setOnSafeClickListener { item.onItemClickListener() }
        }

        override fun update(item: CharacterItem, keys: Set<String>) {
            item as CharacterItem.HorizontalItem

            keys.forEach { key ->
                when (key) {
                    CharacterDiffCallback.DIFF_NAME -> updateName(item)
                    CharacterDiffCallback.DIFF_IMAGE_URL -> updateAvatar(item)
                    CharacterDiffCallback.DIFF_STATUS -> updateStatus(item)
                    CharacterDiffCallback.DIFF_SPECIES -> updateSpecies(item)
                }
            }
        }

        private fun updateAvatar(item: CharacterItem.HorizontalItem): Unit = with(viewBinding) {
            Glide.with(root)
                .load(item.imageUrl)
                .circleCrop()
                .into(ivAvatar)
        }

        private fun updateStatus(item: CharacterItem.HorizontalItem): Unit = with(viewBinding) {
            tvStatus.text = root.context.getString(item.statusNameResId)
            tvStatus.setTextColor(root.color(item.statusColorResId))
        }

        private fun updateName(item: CharacterItem.HorizontalItem): Unit = with(viewBinding) {
            tvName.text = item.name
        }

        private fun updateSpecies(item: CharacterItem.HorizontalItem): Unit = with(viewBinding) {
            tvSpecies.text = item.species
        }
    }

    internal class CharacterGridViewHolder(
        private val viewBinding: ItemCharacterGridBinding,
    ) : BaseViewHolder<CharacterItem>(viewBinding.root) {

        override fun onBind(item: CharacterItem): Unit = with(viewBinding) {
            item as CharacterItem.GridItem

            updateAvatar(item)
            updateStatus(item)
            updateName(item)
            updateSpecies(item)

            root.setOnSafeClickListener { item.onItemClickListener() }
        }

        override fun update(item: CharacterItem, keys: Set<String>) {
            item as CharacterItem.GridItem

            keys.forEach { key ->
                when (key) {
                    CharacterDiffCallback.DIFF_NAME -> updateName(item)
                    CharacterDiffCallback.DIFF_IMAGE_URL -> updateAvatar(item)
                    CharacterDiffCallback.DIFF_STATUS -> updateStatus(item)
                    CharacterDiffCallback.DIFF_SPECIES -> updateSpecies(item)
                }
            }
        }

        private fun updateAvatar(item: CharacterItem.GridItem): Unit = with(viewBinding) {
            Glide.with(root)
                .load(item.imageUrl)
                .circleCrop()
                .into(ivAvatar)
        }

        private fun updateStatus(item: CharacterItem.GridItem): Unit = with(viewBinding) {
            tvStatus.text = root.context.getString(item.statusNameResId)
            tvStatus.setTextColor(root.color(item.statusColorResId))
        }

        private fun updateName(item: CharacterItem.GridItem): Unit = with(viewBinding) {
            tvName.text = item.name
        }

        private fun updateSpecies(item: CharacterItem.GridItem): Unit = with(viewBinding) {
            tvSpecies.text = item.species
        }
    }

    companion object {

        private const val VIEW_HEADER = 0
        private const val VIEW_HORIZONTAL = 1
        private const val VIEW_GRID = 2
    }
}