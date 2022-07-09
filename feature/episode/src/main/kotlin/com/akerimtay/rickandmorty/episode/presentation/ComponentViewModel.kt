package com.akerimtay.rickandmorty.episode.presentation

import androidx.lifecycle.ViewModel
import com.akerimtay.rickandmorty.episode.EpisodeComponentHolder

internal class ComponentViewModel : ViewModel() {

    val component = EpisodeComponentHolder.getComponent()
}