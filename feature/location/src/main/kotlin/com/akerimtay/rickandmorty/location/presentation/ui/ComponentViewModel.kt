package com.akerimtay.rickandmorty.location.presentation.ui

import androidx.lifecycle.ViewModel
import com.akerimtay.rickandmorty.location.LocationComponentHolder

internal class ComponentViewModel : ViewModel() {

    val component = LocationComponentHolder.getComponent()
}