package com.akerimtay.rickandmorty.location.presentation.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import androidx.recyclerview.widget.RecyclerView
import com.akerimtay.rickandmorty.core.presentation.base.BaseViewModel
import com.akerimtay.rickandmorty.location.R
import com.akerimtay.rickandmorty.location.domain.GetLocationsAsFlowUseCase
import com.akerimtay.rickandmorty.location.domain.GetLocationsCountAsFlowUseCase
import com.akerimtay.rickandmorty.location.presentation.model.LocationItem
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn

internal class LocationsViewModel(
    getLocationsCountAsFlowUseCase: GetLocationsCountAsFlowUseCase,
    getLocationsAsFlowUseCase: GetLocationsAsFlowUseCase
) : BaseViewModel() {

    private val _actions = Channel<LocationsAction>(Channel.BUFFERED)
    val actions: Flow<LocationsAction> = _actions.receiveAsFlow()

    private val loadStates = MutableStateFlow<CombinedLoadStates?>(null)

    val isSwipeRefreshing: Flow<Boolean> = loadStates.map { states -> states?.refresh is LoadState.Loading }

    val items: Flow<PagingData<LocationItem>> = getLocationsAsFlowUseCase(Unit)
        .map { pagingData ->
            pagingData.map { location ->
                LocationItem(
                    id = location.id,
                    // Sample image for all items, because API don't provide pictures
                    imageResId = R.drawable.sample_location,
                    name = location.name,
                    type = location.type,
                    dimension = location.dimension,
                    onItemClickListener = {

                    }
                )
            }
        }
        .cachedIn(viewModelScope)

    val locationsCount: Flow<Int> = getLocationsCountAsFlowUseCase(Unit)

    private val firstVisibleItemPosition = MutableStateFlow(RecyclerView.NO_POSITION)
    val isScrollToTopVisible = firstVisibleItemPosition
        .mapLatest { it != RecyclerView.NO_POSITION && it > LAST_VISIBLE_POSITION }
        .stateIn(viewModelScope, SharingStarted.Eagerly, false)

    fun onLoadStates(state: CombinedLoadStates) {
        loadStates.value = state
    }

    fun onScrolled(firstVisibleItemPosition: Int?) {
        this.firstVisibleItemPosition.value = firstVisibleItemPosition ?: RecyclerView.NO_POSITION
    }

    fun onScrollToTopClicked() {
        _actions.trySend(LocationsAction.ScrollToPosition(0))
    }

    companion object {

        private const val LAST_VISIBLE_POSITION = 2
    }
}

internal sealed class LocationsAction {
    data class ScrollToPosition(val position: Int) : LocationsAction()
}

internal class LocationsViewModelFactory @Inject constructor(
    private val getLocationsCountAsFlowUseCase: GetLocationsCountAsFlowUseCase,
    private val getLocationsAsFlowUseCase: GetLocationsAsFlowUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LocationsViewModel(
            getLocationsCountAsFlowUseCase = getLocationsCountAsFlowUseCase,
            getLocationsAsFlowUseCase = getLocationsAsFlowUseCase
        ) as T
    }
}