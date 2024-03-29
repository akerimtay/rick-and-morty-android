package com.akerimtay.rickandmorty.characters.data

import com.akerimtay.rickandmorty.characters.data.remote.model.CharacterResponse
import com.akerimtay.rickandmorty.characters.data.remote.model.CharactersResponse
import com.akerimtay.rickandmorty.core.common.model.Character
import com.akerimtay.rickandmorty.core.common.model.CharacterStatus
import com.akerimtay.rickandmorty.core.common.model.Characters
import com.akerimtay.rickandmorty.core.common.model.Gender

internal object CharacterMapper {

    fun fromNetwork(response: CharacterResponse): Character {
        return Character(
            id = response.id,
            name = response.name,
            status = CharacterStatus.toCharacterStatus(response.status),
            species = response.species,
            type = response.type,
            gender = Gender.toGender(response.gender),
            origin = Character.Location(
                name = response.origin.name,
                url = response.origin.url
            ),
            location = Character.Location(
                name = response.location.name,
                url = response.location.url
            ),
            imageUrl = response.image,
            episode = response.episode,
            url = response.url,
            createdDate = response.created
        )
    }

    fun fromNetwork(response: CharactersResponse): Characters {
        return Characters(
            info = Characters.Info(
                count = response.infoResponse.count,
                pages = response.infoResponse.pages,
                next = response.infoResponse.next,
                prev = response.infoResponse.prev
            ),
            results = response.results.map { fromNetwork(it) }
        )
    }
}