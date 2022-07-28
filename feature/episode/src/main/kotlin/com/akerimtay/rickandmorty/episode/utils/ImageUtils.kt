package com.akerimtay.rickandmorty.episode.utils

import androidx.annotation.DrawableRes
import com.akerimtay.rickandmorty.episode.R

object ImageUtils {

    private val images = listOf(
        R.drawable.sample_episode_1,
        R.drawable.sample_episode_2,
        R.drawable.sample_episode_3,
        R.drawable.sample_episode_4,
        R.drawable.sample_episode_5,
        R.drawable.sample_episode_6,
        R.drawable.sample_episode_7
    )

    @DrawableRes
    fun getRandomImage(): Int {
        return images.random()
    }
}