package com.akerimtay.rickandmorty.characters.presentation.list.adapter

import com.akerimtay.rickandmorty.characters.databinding.ItemCharacterLinearBinding
import com.akerimtay.rickandmorty.characters.presentation.list.adapter.CharacterDiffCallback.Companion.DIFF_IMAGE_URL
import com.akerimtay.rickandmorty.characters.presentation.list.adapter.CharacterDiffCallback.Companion.DIFF_NAME
import com.akerimtay.rickandmorty.characters.presentation.list.adapter.CharacterDiffCallback.Companion.DIFF_SPECIES
import com.akerimtay.rickandmorty.characters.presentation.list.adapter.CharacterDiffCallback.Companion.DIFF_STATUS
import com.akerimtay.rickandmorty.characters.presentation.model.CharacterItem
import com.akerimtay.rickandmorty.core.presentation.base.BaseViewHolder
import com.akerimtay.rickandmorty.core.presentation.util.extensions.color
import com.bumptech.glide.Glide

class CharacterLinearViewHolder(
    private val viewBinding: ItemCharacterLinearBinding,
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
                DIFF_NAME -> updateName(item)
                DIFF_IMAGE_URL -> updateAvatar(item)
                DIFF_STATUS -> updateStatus(item)
                DIFF_SPECIES -> updateSpecies(item)
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