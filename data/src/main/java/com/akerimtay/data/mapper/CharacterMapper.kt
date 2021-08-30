package com.akerimtay.data.mapper

import com.akerimtay.data.db.entity.CharacterEntity
import com.akerimtay.data.remote.model.CharacterResponse
import com.akerimtay.data.remote.model.CharactersResponse
import com.akerimtay.domain.model.Character
import com.akerimtay.domain.model.Characters
import com.akerimtay.domain.model.Location

object CharacterMapper {
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
            createdDate = response.created
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

    fun fromDatabase(entity: CharacterEntity) =
        Character(
            id = entity.id,
            name = entity.name,
            status = entity.status,
            species = entity.species,
            type = entity.type,
            gender = entity.gender,
            origin = Location(
                name = entity.originName,
                url = entity.originUrl
            ),
            location = Location(
                name = entity.locationName,
                url = entity.locationUrl
            ),
            image = entity.image,
            episode = entity.episode,
            url = entity.url,
            createdDate = entity.createdDate
        )

    fun toDatabase(model: Character) =
        CharacterEntity(
            id = model.id,
            name = model.name,
            status = model.status,
            species = model.species,
            type = model.type,
            gender = model.gender,
            originName = model.origin.name,
            originUrl = model.origin.url,
            locationName = model.location.name,
            locationUrl = model.location.url,
            image = model.image,
            episode = model.episode,
            url = model.url,
            createdDate = model.createdDate
        )
}