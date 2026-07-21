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

     //   @Query("SELECT * FROM ITEMS WHERE ID = :id")
     //   fun getItem(id: Int): Flow<Item>

        //@Query("SELECT * FROM ITEMS ORDER BY NAME ASC")
      //  fun getAllItems(): Flow<List<Item>>


}