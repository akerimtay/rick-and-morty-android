package com.akerimtay.rickandmorty.characters.presentation.list.adapter

import androidx.recyclerview.widget.DiffUtil
import com.akerimtay.rickandmorty.characters.presentation.model.CharacterItem

class CharacterDiffCallback : DiffUtil.ItemCallback<CharacterItem>() {

    override fun areItemsTheSame(oldItem: CharacterItem, newItem: CharacterItem): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: CharacterItem, newItem: CharacterItem): Boolean {
        return oldItem.name == newItem.name &&
            oldItem.imageUrl == newItem.imageUrl &&
            oldItem.status == newItem.status &&
            oldItem.species == newItem.species
    }

    override fun getChangePayload(oldItem: CharacterItem, newItem: CharacterItem): Any? {
        val diff = mutableSetOf<String>()
        if (oldItem.name != newItem.name) diff.add(DIFF_NAME)
        if (oldItem.imageUrl != newItem.imageUrl) diff.add(DIFF_IMAGE_URL)
        if (oldItem.status != newItem.status) diff.add(DIFF_STATUS)
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