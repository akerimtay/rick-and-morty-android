package com.akerimtay.rickandmorty.character.ui

import com.akerimtay.rickandmorty.character.domain.model.Character
import com.akerimtay.rickandmorty.common.adapter.BaseContentItem
import com.akerimtay.rickandmorty.content.ItemContentType

class CharacterItem(
    val character: Character,
    val onItemClickListener: () -> Unit
) : BaseContentItem<ItemContentType>(character.id.toString()) {
    override val type: ItemContentType = ItemContentType.CHARACTER
}