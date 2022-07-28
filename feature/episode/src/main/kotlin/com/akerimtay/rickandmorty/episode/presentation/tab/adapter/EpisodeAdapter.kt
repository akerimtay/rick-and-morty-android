package com.akerimtay.rickandmorty.episode.presentation.tab.adapter

import android.view.ViewGroup
import com.akerimtay.rickandmorty.core.presentation.base.BaseListAdapter
import com.akerimtay.rickandmorty.core.presentation.base.BaseViewHolder
import com.akerimtay.rickandmorty.core.presentation.util.extensions.layoutInflater
import com.akerimtay.rickandmorty.episode.databinding.ItemEpisodeBinding
import com.akerimtay.rickandmorty.episode.presentation.model.EpisodeItem

class EpisodeAdapter : BaseListAdapter<EpisodeItem>(EpisodeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<EpisodeItem> {
        return EpisodeViewHolder(ItemEpisodeBinding.inflate(parent.layoutInflater, parent, false))
    }
}