package com.akerimtay.rickandmorty.location.data.repository

import androidx.paging.PagingData
import com.akerimtay.rickandmorty.core.common.model.Location
import kotlinx.coroutines.flow.Flow

interface LocationRepository {

    val locationsCount: Flow<Int>

    fun getAsPagingData(): Flow<PagingData<Location>>
}