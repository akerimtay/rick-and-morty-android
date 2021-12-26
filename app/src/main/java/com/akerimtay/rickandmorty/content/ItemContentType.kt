package com.akerimtay.rickandmorty.content

import android.view.View
import com.akerimtay.rickandmorty.R
import com.akerimtay.rickandmorty.character.ui.CharacterViewHolder
import com.akerimtay.rickandmorty.common.adapter.ContentType

enum class ItemContentType : ContentType {
    CHARACTER {
        override fun type(): Int = ordinal
        override fun getLayout(): Int = R.layout.item_character
        override fun createHolder(view: View) = CharacterViewHolder(view)
    }
}