package com.akerimtay.rickandmorty.location.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.akerimtay.rickandmorty.core.presentation.base.BaseViewModel
import com.akerimtay.rickandmorty.location.R
import com.akerimtay.rickandmorty.location.domain.GetLocationsAsFlowUseCase
import com.akerimtay.rickandmorty.location.domain.GetLocationsCountAsFlowUseCase
import com.akerimtay.rickandmorty.location.presentation.model.LocationItem
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

internal class LocationsViewModel(
    getLocationsCountAsFlowUseCase: GetLocationsCountAsFlowUseCase,
    getLocationsAsFlowUseCase: GetLocationsAsFlowUseCase
) : BaseViewModel() {

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

    fun onLoadStates(state: CombinedLoadStates) {
        loadStates.value = state
    }
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