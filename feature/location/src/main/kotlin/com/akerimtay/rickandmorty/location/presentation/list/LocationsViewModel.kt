package com.akerimtay.rickandmorty.location.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akerimtay.rickandmorty.core.presentation.base.BaseViewModel
import com.akerimtay.rickandmorty.location.domain.GetLocationsAsFlowUseCase
import com.akerimtay.rickandmorty.location.domain.GetLocationsCountAsFlowUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

internal class LocationsViewModel(
    getLocationsCountAsFlowUseCase: GetLocationsCountAsFlowUseCase,
    private val getLocationsAsFlowUseCase: GetLocationsAsFlowUseCase
) : BaseViewModel() {

    val locationsCount: Flow<Int> = getLocationsCountAsFlowUseCase(Unit)
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