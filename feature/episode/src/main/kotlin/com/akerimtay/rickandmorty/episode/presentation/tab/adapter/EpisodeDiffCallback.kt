package com.akerimtay.rickandmorty.episode.presentation.tab.adapter

import androidx.recyclerview.widget.DiffUtil
import com.akerimtay.rickandmorty.episode.presentation.model.EpisodeItem

class EpisodeDiffCallback : DiffUtil.ItemCallback<EpisodeItem>() {

    override fun areItemsTheSame(oldItem: EpisodeItem, newItem: EpisodeItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: EpisodeItem, newItem: EpisodeItem): Boolean {
        return oldItem.id == newItem.id &&
            oldItem.name == newItem.name &&
            oldItem.number == newItem.number &&
            oldItem.date == newItem.date &&
            oldItem.imageResId == newItem.imageResId
    }

    override fun getChangePayload(oldItem: EpisodeItem, newItem: EpisodeItem): Any? {
        val diff = mutableSetOf<String>()
        if (oldItem.name != newItem.name) diff.add(DIFF_NAME)
        if (oldItem.number != newItem.number) diff.add(DIFF_NUMBER)
        if (oldItem.date != newItem.date) diff.add(DIFF_DATE)
        if (oldItem.imageResId != newItem.imageResId) diff.add(DIFF_IMAGE)
        return diff.takeIf { it.isNotEmpty() }
    }

    companion object {

        const val DIFF_NAME = "DIFF_NAME"
        const val DIFF_NUMBER = "DIFF_NUMBER"
        const val DIFF_DATE = "DIFF_DATE"
        const val DIFF_IMAGE = "DIFF_IMAGE"
    }
}