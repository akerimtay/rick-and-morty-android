package com.akerimtay.network.adapter

import com.akerimtay.common.model.Gender
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type

class GenderTypeAdapter : JsonSerializer<Gender?>, JsonDeserializer<Gender> {
    override fun serialize(src: Gender?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement =
        JsonPrimitive(src?.serializedName)

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Gender =
        Gender.toGender(json?.asString.orEmpty())
}