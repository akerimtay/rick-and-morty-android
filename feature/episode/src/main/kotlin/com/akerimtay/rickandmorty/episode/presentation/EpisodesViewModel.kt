package com.akerimtay.rickandmorty.episode.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akerimtay.rickandmorty.core.presentation.base.BaseViewModel
import com.akerimtay.rickandmorty.episode.domain.GetEpisodesUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber

internal class EpisodesViewModel(
    private val getEpisodesUseCase: GetEpisodesUseCase,
) : BaseViewModel() {

    private val _episodes = MutableStateFlow<List<Any>>(emptyList())
    val episodes: StateFlow<List<Any>> = _episodes.asStateFlow()

    init {
        launchSafe(
            body = {
                _episodes.value = getEpisodesUseCase(Unit)
            },
            handleError = { throwable ->
                Timber.e(throwable, "Couldn't load episodes")
            }
        )
    }
}

internal class EpisodesViewModelFactory @Inject constructor(
    private val getEpisodesUseCase: GetEpisodesUseCase,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EpisodesViewModel(
            getEpisodesUseCase = getEpisodesUseCase,
        ) as T
    }
}