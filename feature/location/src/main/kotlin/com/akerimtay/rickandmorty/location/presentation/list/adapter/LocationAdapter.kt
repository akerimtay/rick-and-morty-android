package com.akerimtay.rickandmorty.location.presentation.list.adapter

import android.view.ViewGroup
import com.akerimtay.rickandmorty.core.presentation.base.BasePagingAdapter
import com.akerimtay.rickandmorty.core.presentation.base.BaseViewHolder
import com.akerimtay.rickandmorty.core.presentation.util.extensions.layoutInflater
import com.akerimtay.rickandmorty.location.databinding.ItemLocationBinding
import com.akerimtay.rickandmorty.location.presentation.model.LocationItem

class LocationAdapter : BasePagingAdapter<LocationItem>(LocationDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<LocationItem> {
        return LocationViewHolder(ItemLocationBinding.inflate(parent.layoutInflater, parent, false))
    }
}