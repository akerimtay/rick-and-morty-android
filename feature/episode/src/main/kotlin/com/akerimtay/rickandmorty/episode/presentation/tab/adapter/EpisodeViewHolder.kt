package com.akerimtay.rickandmorty.episode.presentation.tab.adapter

import com.akerimtay.rickandmorty.core.presentation.base.BaseViewHolder
import com.akerimtay.rickandmorty.core.presentation.util.extensions.setOnSafeClickListener
import com.akerimtay.rickandmorty.episode.R
import com.akerimtay.rickandmorty.episode.databinding.ItemEpisodeBinding
import com.akerimtay.rickandmorty.episode.presentation.model.EpisodeItem
import com.akerimtay.rickandmorty.episode.presentation.tab.adapter.EpisodeDiffCallback.Companion.DIFF_DATE
import com.akerimtay.rickandmorty.episode.presentation.tab.adapter.EpisodeDiffCallback.Companion.DIFF_IMAGE
import com.akerimtay.rickandmorty.episode.presentation.tab.adapter.EpisodeDiffCallback.Companion.DIFF_NAME
import com.akerimtay.rickandmorty.episode.presentation.tab.adapter.EpisodeDiffCallback.Companion.DIFF_NUMBER
import com.bumptech.glide.Glide

class EpisodeViewHolder(
    private val viewBinding: ItemEpisodeBinding,
) : BaseViewHolder<EpisodeItem>(viewBinding.root) {

    override fun onBind(item: EpisodeItem): Unit = with(viewBinding) {
        updateName(item)
        updateNumber(item)
        updateDate(item)
        updateImage(item)

        root.setOnSafeClickListener { item.onItemClickListener() }
    }

    override fun update(item: EpisodeItem, keys: Set<String>) {
        keys.forEach { key ->
            when (key) {
                DIFF_NAME -> updateName(item)
                DIFF_NUMBER -> updateNumber(item)
                DIFF_DATE -> updateDate(item)
                DIFF_IMAGE -> updateImage(item)
            }
        }
    }

    private fun updateName(item: EpisodeItem): Unit = with(viewBinding) {
        tvName.text = item.name
    }

    private fun updateNumber(item: EpisodeItem): Unit = with(viewBinding) {
        tvNumber.text = root.context.getString(R.string.episode_n, item.number)
    }

    private fun updateDate(item: EpisodeItem): Unit = with(viewBinding) {
        tvDate.text = item.date
    }

    private fun updateImage(item: EpisodeItem): Unit = with(viewBinding) {
        Glide.with(root)
            .load(item.imageResId)
            .into(ivAvatar)
    }
}