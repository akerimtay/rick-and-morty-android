package com.akerimtay.rickandmorty.characters.presentation.ui.list.adapter

import android.view.ViewGroup
import com.akerimtay.rickandmorty.characters.databinding.ItemCharacterBinding
import com.akerimtay.rickandmorty.characters.presentation.model.CharacterItem
import com.akerimtay.rickandmorty.core.presentation.base.BasePagingAdapter
import com.akerimtay.rickandmorty.core.presentation.base.BaseViewHolder
import com.akerimtay.rickandmorty.core.presentation.util.extensions.layoutInflater

class CharacterAdapter : BasePagingAdapter<CharacterItem>(CharacterDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<CharacterItem> {
        return CharacterViewHolder(ItemCharacterBinding.inflate(parent.layoutInflater, parent, false))
    }
}