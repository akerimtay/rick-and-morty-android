package com.akerimtay.rickandmorty.characters.presentation.list.adapter

import android.view.ViewGroup
import com.akerimtay.rickandmorty.characters.databinding.ItemCharacterGridBinding
import com.akerimtay.rickandmorty.characters.databinding.ItemCharacterLinearBinding
import com.akerimtay.rickandmorty.characters.presentation.model.CharacterItem
import com.akerimtay.rickandmorty.core.presentation.base.BasePagingAdapter
import com.akerimtay.rickandmorty.core.presentation.base.BaseViewHolder
import com.akerimtay.rickandmorty.core.presentation.util.extensions.layoutInflater

class CharacterAdapter(
    private val orientationType: OrientationType = OrientationType.LINEAR
) : BasePagingAdapter<CharacterItem>(CharacterDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<CharacterItem> {
        return when (orientationType) {
            OrientationType.LINEAR -> CharacterLinearViewHolder(
                ItemCharacterLinearBinding.inflate(parent.layoutInflater, parent, false)
            )
            OrientationType.GRID -> CharacterGridViewHolder(
                ItemCharacterGridBinding.inflate(parent.layoutInflater, parent, false)
            )
        }
    }

    enum class OrientationType {
        LINEAR, GRID
    }
}