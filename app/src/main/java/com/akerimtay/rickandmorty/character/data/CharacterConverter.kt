package com.akerimtay.rickandmorty.character.data

import com.akerimtay.rickandmorty.character.data.api.model.CharacterResponse
import com.akerimtay.rickandmorty.character.data.api.model.CharactersResponse
import com.akerimtay.rickandmorty.character.model.Character
import com.akerimtay.rickandmorty.character.model.Characters
import com.akerimtay.rickandmorty.character.model.Location

object CharacterConverter {
    fun fromNetwork(response: CharacterResponse) =
        Character(
            id = response.id,
            name = response.name,
            status = response.status,
            species = response.species,
            type = response.type,
            gender = response.gender,
            origin = Location(
                name = response.origin.name,
                url = response.origin.url
            ),
            location = Location(
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