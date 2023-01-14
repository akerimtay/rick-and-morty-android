package com.akerimtay.rickandmorty.episode.presentation.ui.tab

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akerimtay.rickandmorty.core.common.model.Episode
import com.akerimtay.rickandmorty.core.presentation.base.BaseViewModel
import com.akerimtay.rickandmorty.episode.domain.GetEpisodesUseCase
import com.akerimtay.rickandmorty.episode.presentation.model.EpisodeItem
import com.akerimtay.rickandmorty.episode.presentation.model.PageType
import com.akerimtay.rickandmorty.episode.utils.ImageUtils
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import timber.log.Timber

internal class EpisodesViewModel(
    private val pageType: PageType,
    private val getEpisodesUseCase: GetEpisodesUseCase
) : BaseViewModel() {

    private val _isSwipeRefreshing = MutableStateFlow(false)
    val isSwipeRefreshing: Flow<Boolean> = _isSwipeRefreshing

    private val _episodes = MutableStateFlow<List<Episode>>(emptyList())
    val episodes: Flow<List<EpisodeItem>> = _episodes
        .map { episodes ->
            episodes.map { episode ->
                EpisodeItem(
                    id = episode.id,
                    name = episode.name,
                    number = episode.code.getEpisodeNumber(),
                    date = episode.date,
                    // Sample image for items, because API don't provide pictures
                    imageResId = ImageUtils.getRandomImage(),
                    onItemClickListener = {

                    }
                )
            }
        }

    init {
        refresh()
    }

    fun refresh() {
        launchSafe(
            start = { _isSwipeRefreshing.value = true },
            finish = { _isSwipeRefreshing.value = false },
            body = {
                _episodes.value = getEpisodesUseCase(GetEpisodesUseCase.Param(pageType.seasonCode))
            },
            handleError = {
                Timber.e(it, "Couldn't load episodes")
            }
        )
    }

    private fun String.getEpisodeNumber(): Int {
        return substringAfter('E').toInt()
    }
}

internal class EpisodesViewModelFactory @AssistedInject constructor(
    @Assisted private val pageType: PageType,
    private val getEpisodesUseCase: GetEpisodesUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EpisodesViewModel(
            pageType = pageType,
            getEpisodesUseCase = getEpisodesUseCase
        ) as T
    }

    @AssistedFactory
    interface Factory {

        fun create(@Assisted pageType: PageType): EpisodesViewModelFactory
    }
}