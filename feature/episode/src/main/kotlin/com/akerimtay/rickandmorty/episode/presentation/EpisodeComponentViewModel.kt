package com.akerimtay.rickandmorty.episode.presentation

import com.akerimtay.rickandmorty.core.presentation.base.BaseViewModel
import com.akerimtay.rickandmorty.episode.EpisodeComponentHolder

internal class EpisodeComponentViewModel : BaseViewModel() {

    val component = EpisodeComponentHolder.getComponent()
}