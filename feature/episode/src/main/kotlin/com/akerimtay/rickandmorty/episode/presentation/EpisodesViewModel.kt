package com.akerimtay.rickandmorty.episode.presentation

import com.akerimtay.rickandmorty.core.presentation.base.BaseViewModel
import com.akerimtay.rickandmorty.entity.Character
import com.akerimtay.rickandmorty.episode.domain.GetCharactersUseCase
import com.akerimtay.rickandmorty.episode.domain.GetEpisodesUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber

internal class EpisodesViewModel @Inject constructor(
    private val getEpisodesUseCase: GetEpisodesUseCase,
    private val getCharactersUseCase: GetCharactersUseCase
) : BaseViewModel() {

    private val _episodes = MutableStateFlow<List<Any>>(emptyList())
    val episodes: StateFlow<List<Any>> = _episodes.asStateFlow()

    private val _characters = MutableStateFlow<List<Character>>(emptyList())
    val characters: StateFlow<List<Character>> = _characters.asStateFlow()

    init {
        launchSafe(
            body = {
                _episodes.value = getEpisodesUseCase(Unit)
                _characters.value = getCharactersUseCase(Unit)
            },
            handleError = { throwable ->
                Timber.e(throwable, "Couldn't load episodes")
            }
        )
    }
}