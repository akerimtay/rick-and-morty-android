package com.akerimtay.rickandmorty.episode.presentation.tab

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akerimtay.rickandmorty.core.common.model.Episode
import com.akerimtay.rickandmorty.core.presentation.base.BaseViewModel
import com.akerimtay.rickandmorty.episode.R
import com.akerimtay.rickandmorty.episode.domain.GetEpisodesUseCase
import com.akerimtay.rickandmorty.episode.presentation.model.EpisodeItem
import com.akerimtay.rickandmorty.episode.presentation.model.PageType
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

    private val _episodes = MutableStateFlow<List<Episode>>(emptyList())
    val episodes: Flow<List<EpisodeItem>> = _episodes
        .map { episodes ->
            episodes.map { episode ->
                EpisodeItem(
                    id = episode.id,
                    name = episode.name,
                    season = episode.code,
                    date = episode.date,
                    // Sample image for items, because API don't provide pictures
                    imageResId = R.drawable.sample_location,
                    onItemClickListener = {
                        Timber.e("onClick: ${episode.id}")
                    }
                )
            }
        }

    init {
        launchSafe(
            body = {
                _episodes.value = getEpisodesUseCase(GetEpisodesUseCase.Param(pageType.seasonCode))
            },
            handleError = {
                Timber.e(it, "Couldn't load episodes")
            }
        )
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