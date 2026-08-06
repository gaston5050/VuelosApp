package com.example.vuelosapp.data


data class VuelosUiState(
    val textoBusqueda: String = "",
    val listadoAeropuertosFiltrados: List<Airport> = emptyList(),
    val listaFavoritos: List<Favorite> = emptyList(),
    val aeropuertoSeleccionado: Airport ?= null
)

