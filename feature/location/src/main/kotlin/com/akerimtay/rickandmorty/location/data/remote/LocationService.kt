package com.akerimtay.rickandmorty.location.data.remote

import com.akerimtay.rickandmorty.location.data.remote.model.LocationsResponse
import retrofit2.http.GET

internal interface LocationService {

    @GET("location")
    suspend fun getLocations(): LocationsResponse
}