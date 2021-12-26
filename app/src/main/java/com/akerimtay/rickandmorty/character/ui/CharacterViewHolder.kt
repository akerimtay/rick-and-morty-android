package com.akerimtay.rickandmorty.character.ui

import android.view.View
import com.akerimtay.rickandmorty.common.adapter.BaseHolder
import com.akerimtay.rickandmorty.common.util.setThrottleOnClickListener
import com.akerimtay.rickandmorty.content.ItemContentType
import com.akerimtay.rickandmorty.databinding.ItemCharacterBinding

class CharacterViewHolder(
    containerView: View
) : BaseHolder<ItemContentType, CharacterItem>(containerView) {

    private val binding = ItemCharacterBinding.bind(containerView)

    init {
        binding.root.setThrottleOnClickListener { getItem()?.onItemClickListener?.invoke() }
    }

    override fun bindItem(item: CharacterItem) {
        binding.nameTextView.text = item.character.name
        binding.statusTextView.text = item.character.status.serializedName
    }
}