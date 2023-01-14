package com.akerimtay.rickandmorty.location.domain

import androidx.paging.PagingData
import com.akerimtay.rickandmorty.core.common.model.Location
import com.akerimtay.rickandmorty.core.common.usecase.FlowUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

internal class GetLocationsAsFlowUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) : FlowUseCase<Unit, PagingData<Location>>() {

    override fun execute(parameters: Unit): Flow<PagingData<Location>> {
        return locationRepository.getAsPagingData()
    }
}