package com.akerimtay.rickandmorty.location.data

import com.akerimtay.rickandmorty.core.common.model.Location
import com.akerimtay.rickandmorty.core.common.model.Locations
import com.akerimtay.rickandmorty.location.data.remote.model.LocationResponse
import com.akerimtay.rickandmorty.location.data.remote.model.LocationsResponse

internal object LocationMapper {

    fun fromNetwork(response: LocationResponse): Location {
        return Location(
            id = response.id,
            name = response.name,
            type = response.type,
            dimension = response.dimension,
            residents = response.residents
        )
    }

    fun fromNetwork(response: LocationsResponse): Locations {
        return Locations(
            info = Locations.Info(
                count = response.infoResponse.count,
                pages = response.infoResponse.pages,
                next = response.infoResponse.next,
                prev = response.infoResponse.prev
            ),
            results = response.results.map { fromNetwork(it) }
        )
    }
}