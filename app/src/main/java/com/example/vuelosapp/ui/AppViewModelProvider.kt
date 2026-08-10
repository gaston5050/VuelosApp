package com.example.vuelosapp.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.vuelosapp.VuelosApplication
import com.example.vuelosapp.ui.screen.inicio.InicioViewModel
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer

object AppViewModelProvider {

    val Factory = viewModelFactory {
        initializer {
        val application =
            (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as VuelosApplication)

        val container = application.container

        InicioViewModel(
            flightRepository = container.vuelosRepository,
            userPreferencesRepository = container.userPreferencesRepository
        )

    }
    }
}