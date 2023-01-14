package com.akerimtay.rickandmorty.location.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.akerimtay.rickandmorty.core.common.model.Location
import com.akerimtay.rickandmorty.location.data.paging.LocationPagingSource
import com.akerimtay.rickandmorty.location.data.remote.LocationRemoteDataSource
import com.akerimtay.rickandmorty.location.domain.LocationRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

internal class LocationRepositoryImpl @Inject constructor(
    private val locationRemoteDataSource: LocationRemoteDataSource
) : LocationRepository {

    override val locationsCount: Flow<Int>
        get() = locationRemoteDataSource.locationsCount

    override fun getAsPagingData(): Flow<PagingData<Location>> {
        return Pager(
            config = PagingConfig(
                pageSize = DEFAULT_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { LocationPagingSource(locationRemoteDataSource = locationRemoteDataSource) }
        ).flow
    }

    companion object {

        private const val DEFAULT_PAGE_SIZE = 20
    }
}