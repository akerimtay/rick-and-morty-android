package com.akerimtay.rickandmorty.navigation

import androidx.lifecycle.ViewModel
import com.akerimtay.rickandmorty.navigation.navigator.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NavigatorViewModel @Inject constructor(
    private val navigator: Navigator
) : ViewModel(), Navigator by navigator