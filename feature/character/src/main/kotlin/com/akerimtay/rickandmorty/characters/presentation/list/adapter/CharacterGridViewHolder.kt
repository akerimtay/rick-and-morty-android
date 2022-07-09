package com.akerimtay.rickandmorty.characters.presentation.list.adapter

import com.akerimtay.rickandmorty.characters.databinding.ItemCharacterGridBinding
import com.akerimtay.rickandmorty.characters.presentation.model.CharacterItem
import com.akerimtay.rickandmorty.core.presentation.base.BaseViewHolder
import com.akerimtay.rickandmorty.core.presentation.util.extensions.color
import com.bumptech.glide.Glide

class CharacterGridViewHolder(
    private val viewBinding: ItemCharacterGridBinding
) : BaseViewHolder<CharacterItem>(viewBinding.root) {

    override fun onBind(item: CharacterItem): Unit = with(viewBinding) {
        updateAvatar(item)
        updateStatus(item)
        updateName(item)
        updateSpecies(item)
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