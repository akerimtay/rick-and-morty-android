package com.akerimtay.rickandmorty.location.data.remote

import com.akerimtay.rickandmorty.location.data.remote.model.LocationsResponse
import retrofit2.http.GET
import retrofit2.http.Query

internal interface LocationService {

    @GET("location")
    suspend fun getLocations(
        @Query("page") page: Int? = null,
    ): LocationsResponse
}