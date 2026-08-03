package com.example.vuelosapp.data

import kotlinx.coroutines.flow.Flow

interface VuelosRepository {

    fun getAllAirports(): Flow<List<Airport>>
    fun getPossibleDestinations(input: String): Flow<List<Airport>>
    fun getFavorites(): Flow<List<Favorite>>
    fun addFavorite(favorite: Favorite)
    fun removeFavorite(favorite: Favorite)


}