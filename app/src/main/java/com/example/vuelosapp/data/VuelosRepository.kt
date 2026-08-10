package com.example.vuelosapp.data

import kotlinx.coroutines.flow.Flow

interface VuelosRepository {

    fun getAirports(input: String): Flow<List<Airport>>
    fun getPossibleDestinations(input: String): Flow<List<Airport>>
    fun getFavorites(): Flow<List<Favorite>>

    suspend fun addFavorite(favorite: Favorite)
    suspend fun removeFavorite(favorite: Favorite)

}