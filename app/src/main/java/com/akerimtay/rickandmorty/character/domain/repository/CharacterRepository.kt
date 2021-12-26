package com.akerimtay.rickandmorty.character.domain.repository

import com.akerimtay.rickandmorty.character.domain.model.Character

interface CharacterRepository {

    suspend fun getAll(): List<Character>
}