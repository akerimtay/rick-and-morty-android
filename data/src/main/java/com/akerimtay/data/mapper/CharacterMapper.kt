package com.akerimtay.data.mapper

import com.akerimtay.data.remote.model.CharacterResponse
import com.akerimtay.data.remote.model.CharactersResponse
import com.akerimtay.domain.model.Characters

object CharacterMapper {
    fun fromNetwork(response: CharacterResponse) =
        com.akerimtay.domain.model.Character(
            id = response.id,
            name = response.name,
            status = response.status,
            species = response.species,
            type = response.type,
            gender = response.gender,
            origin = com.akerimtay.domain.model.Location(
                name = response.origin.name,
                url = response.origin.url
            ),
            location = com.akerimtay.domain.model.Location(
                name = response.location.name,
                url = response.location.url
            ),
            image = response.image,
            episode = response.episode,
            url = response.url,
            created = response.created
        )

    fun fromNetwork(response: CharactersResponse): Characters =
        Characters(
            info = Characters.Info(
                count = response.infoResponse.count,
                pages = response.infoResponse.pages,
                next = response.infoResponse.next,
                prev = response.infoResponse.prev
            ),
            results = response.results.map { fromNetwork(it) }
        )
}