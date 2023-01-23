package com.akerimtay.rickandmorty.characters.presentation.ui.list.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.akerimtay.rickandmorty.characters.databinding.ItemCharacterGridBinding
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
        return when (getItem(position)?.viewType) {
            ListViewType.HORIZONTAL -> VIEW_HORIZONTAL
            ListViewType.GRID -> VIEW_GRID
            else -> super.getItemViewType(position)
        }
    }

    internal class CharacterDiffCallback : DiffUtil.ItemCallback<CharacterItem>() {

        override fun areItemsTheSame(oldItem: CharacterItem, newItem: CharacterItem): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: CharacterItem, newItem: CharacterItem): Boolean {
            return oldItem.name == newItem.name &&
                oldItem.imageUrl == newItem.imageUrl &&
                oldItem.statusNameResId == newItem.statusNameResId &&
                oldItem.statusColorResId == newItem.statusColorResId &&
                oldItem.species == newItem.species
        }

        override fun getChangePayload(oldItem: CharacterItem, newItem: CharacterItem): Any? {
            val diff = mutableSetOf<String>()
            if (oldItem.name != newItem.name) diff.add(DIFF_NAME)
            if (oldItem.imageUrl != newItem.imageUrl) diff.add(DIFF_IMAGE_URL)
            if (oldItem.statusNameResId != newItem.statusNameResId ||
                oldItem.statusColorResId != newItem.statusColorResId
            ) diff.add(DIFF_STATUS)
            if (oldItem.species != newItem.species) diff.add(DIFF_SPECIES)
            return diff.takeIf { it.isNotEmpty() }
        }

        companion object {

            const val DIFF_NAME = "DIFF_NAME"
            const val DIFF_IMAGE_URL = "DIFF_IMAGE_URL"
            const val DIFF_STATUS = "DIFF_STATUS"
            const val DIFF_SPECIES = "DIFF_SPECIES"
        }
    }

    internal class CharacterHorizontalViewHolder(
        private val viewBinding: ItemCharacterHorizontalBinding,
    ) : BaseViewHolder<CharacterItem>(viewBinding.root) {

        override fun onBind(item: CharacterItem): Unit = with(viewBinding) {
            updateAvatar(item)
            updateStatus(item)
            updateName(item)
            updateSpecies(item)

            root.setOnSafeClickListener { item.onItemClickListener() }
        }

        override fun update(item: CharacterItem, keys: Set<String>) {
            keys.forEach { key ->
                when (key) {
                    CharacterDiffCallback.DIFF_NAME -> updateName(item)
                    CharacterDiffCallback.DIFF_IMAGE_URL -> updateAvatar(item)
                    CharacterDiffCallback.DIFF_STATUS -> updateStatus(item)
                    CharacterDiffCallback.DIFF_SPECIES -> updateSpecies(item)
                }
            }
        }

        private fun updateAvatar(item: CharacterItem): Unit = with(viewBinding) {
            Glide.with(root)
                .load(item.imageUrl)
                .circleCrop()
                .into(ivAvatar)
        }

        private fun updateStatus(item: CharacterItem): Unit = with(viewBinding) {
            tvStatus.text = root.context.getString(item.statusNameResId)
            tvStatus.setTextColor(root.color(item.statusColorResId))
        }

        private fun updateName(item: CharacterItem): Unit = with(viewBinding) {
            tvName.text = item.name
        }

        private fun updateSpecies(item: CharacterItem): Unit = with(viewBinding) {
            tvSpecies.text = item.species
        }
    }

    internal class CharacterGridViewHolder(
        private val viewBinding: ItemCharacterGridBinding,
    ) : BaseViewHolder<CharacterItem>(viewBinding.root) {

        override fun onBind(item: CharacterItem): Unit = with(viewBinding) {
            updateAvatar(item)
            updateStatus(item)
            updateName(item)
            updateSpecies(item)

            root.setOnSafeClickListener { item.onItemClickListener() }
        }

        override fun update(item: CharacterItem, keys: Set<String>) {
            keys.forEach { key ->
                when (key) {
                    CharacterDiffCallback.DIFF_NAME -> updateName(item)
                    CharacterDiffCallback.DIFF_IMAGE_URL -> updateAvatar(item)
                    CharacterDiffCallback.DIFF_STATUS -> updateStatus(item)
                    CharacterDiffCallback.DIFF_SPECIES -> updateSpecies(item)
                }
            }
        }

        private fun updateAvatar(item: CharacterItem): Unit = with(viewBinding) {
            Glide.with(root)
                .load(item.imageUrl)
                .circleCrop()
                .into(ivAvatar)
        }

        private fun updateStatus(item: CharacterItem): Unit = with(viewBinding) {
            tvStatus.text = root.context.getString(item.statusNameResId)
            tvStatus.setTextColor(root.color(item.statusColorResId))
        }

        private fun updateName(item: CharacterItem): Unit = with(viewBinding) {
            tvName.text = item.name
        }

        private fun updateSpecies(item: CharacterItem): Unit = with(viewBinding) {
            tvSpecies.text = item.species
        }
    }

    companion object {

        private const val VIEW_HORIZONTAL = 0
        private const val VIEW_GRID = 1
    }
}