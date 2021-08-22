package com.akerimtay.rickandmorty.character.data

import com.akerimtay.rickandmorty.character.data.api.CharacterResponse
import com.akerimtay.rickandmorty.character.model.Character
import com.akerimtay.rickandmorty.character.model.Location
import com.akerimtay.rickandmorty.common.BasePagedConverter
import com.akerimtay.rickandmorty.common.BasePagedModel
import com.akerimtay.rickandmorty.network.BasePagedResponse

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

    fun fromNetwork(response: BasePagedResponse<List<CharacterResponse>>): BasePagedModel<List<Character>> =
        BasePagedModel(
            info = BasePagedConverter.fromNetwork(response.infoResponse),
            results = response.results.map { fromNetwork(it) }
        )
}