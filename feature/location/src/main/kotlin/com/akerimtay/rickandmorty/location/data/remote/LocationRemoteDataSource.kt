package com.akerimtay.rickandmorty.location.data.remote

import com.akerimtay.rickandmorty.core.common.model.Location
import kotlinx.coroutines.flow.Flow

internal interface LocationRemoteDataSource {

    val locationsCount: Flow<Int>

    suspend fun getLocations(page: Int? = null): List<Location>
}