package com.akerimtay.rickandmorty.network.adapter

import com.akerimtay.rickandmorty.core.common.model.CharacterStatus
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type

class CharacterStatusTypeAdapter : JsonSerializer<CharacterStatus?>, JsonDeserializer<CharacterStatus> {

    override fun serialize(src: CharacterStatus?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        return JsonPrimitive(src?.serializedName)
    }

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): CharacterStatus {
        return CharacterStatus.toCharacterStatus(json?.asString?.lowercase().orEmpty())
    }
}