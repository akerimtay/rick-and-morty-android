package com.akerimtay.rickandmorty.location.domain

import com.akerimtay.rickandmorty.core.common.usecase.FlowUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

internal class GetLocationsCountAsFlowUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) : FlowUseCase<Unit, Int>() {

    override fun execute(parameters: Unit): Flow<Int> {
        return locationRepository.locationsCount
    }
}