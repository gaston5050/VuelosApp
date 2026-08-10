package com.example.vuelosapp.data

import kotlinx.coroutines.flow.Flow

class OfflineVuelosRepository(private val airportDao: AirportDao): VuelosRepository {

    override fun getAirports(input:String): Flow<List<Airport>>  = airportDao.getAirports(input)

    override fun getPossibleDestinations(input: String): Flow<List<Airport>> = airportDao.getPossibleDestinations(input)

    override fun getFavorites(): Flow<List<Favorite>>  = airportDao.getFavorites()

    override suspend fun addFavorite(favorite: Favorite) = airportDao.insert(favorite)

    override suspend fun removeFavorite(favorite: Favorite) = airportDao.delete(favorite)


}