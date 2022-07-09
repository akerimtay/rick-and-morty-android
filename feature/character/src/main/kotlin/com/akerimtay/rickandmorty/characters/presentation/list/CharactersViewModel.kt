package com.akerimtay.rickandmorty.characters.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akerimtay.rickandmorty.characters.domain.GetCharactersAsFlowUseCase
import com.akerimtay.rickandmorty.characters.domain.GetCharactersCountAsFlowUseCase
import com.akerimtay.rickandmorty.core.presentation.base.BaseViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

internal class CharactersViewModel(
    private val getCharactersAsFlowUseCase: GetCharactersAsFlowUseCase,
    getCharactersCountAsFlowUseCase: GetCharactersCountAsFlowUseCase
) : BaseViewModel() {

    val charactersCount: Flow<Int> = getCharactersCountAsFlowUseCase(Unit)
}

internal class CharactersViewModelFactory @Inject constructor(
    private val getCharactersAsFlowUseCase: GetCharactersAsFlowUseCase,
    private val getCharactersCountAsFlowUseCase: GetCharactersCountAsFlowUseCase,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CharactersViewModel(
            getCharactersAsFlowUseCase = getCharactersAsFlowUseCase,
            getCharactersCountAsFlowUseCase = getCharactersCountAsFlowUseCase
        ) as T
    }
}