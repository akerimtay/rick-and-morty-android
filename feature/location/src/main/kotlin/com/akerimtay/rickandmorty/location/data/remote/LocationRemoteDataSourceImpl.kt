package com.akerimtay.rickandmorty.location.data.remote

import com.akerimtay.rickandmorty.core.common.model.Location
import com.akerimtay.rickandmorty.location.data.LocationMapper
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

internal class LocationRemoteDataSourceImpl @Inject constructor(
    private val locationService: LocationService
) : LocationRemoteDataSource {

    private val _locationsCount = MutableStateFlow(INITIAL_COUNT)
    override val locationsCount: Flow<Int> = _locationsCount

    override suspend fun getLocations(page: Int?): List<Location> {
        val response = locationService.getLocations(page = page)
        val locations = LocationMapper.fromNetwork(response)
        _locationsCount.value = locations.info.count
        return locations.results
    }

    companion object {

        private const val INITIAL_COUNT = 0
    }
}