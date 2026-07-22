package com.example.vuelosapp.data.clases

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface AirportDao {

        @Insert
        suspend fun insert(airport: Airport)

        @Update
        suspend fun update(airport: Airport)

        @Delete
        suspend fun delete(airport: Airport)

        //Query para que devuelva nombre de aeropuerto/ codigo% IATA  a medida que se escribe en el imput
        @Query("SELECT * FROM airport WHERE name like  :input OR iata_code  like :input")
        fun getAirports(input: String): Flow<List<Airport>>

        @Query ("SELECT * FROM airport WHERE iata_code != :input ")
        fun getPossibleDestinations(input: String): Flow<List<Airport>>

        @Query ("Select * from favorite")
        fun getFavorites():Flow<List<Favorite>>

     //   @Query("SELECT * FROM ITEMS WHERE ID = :id")
     //   fun getItem(id: Int): Flow<Item>

        //@Query("SELECT * FROM ITEMS ORDER BY NAME ASC")
      //  fun getAllItems(): Flow<List<Item>>


}