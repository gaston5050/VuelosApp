package com.example.vuelosapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Airport::class, Favorite::class], version = 1, exportSchema = false )
abstract class AirportDatabase: RoomDatabase() {

    //
    abstract fun airportDao(): AirportDao


    companion object {
        @Volatile
        private var Instance: AirportDatabase? = null

        fun getDatabase(context: Context): AirportDatabase{
            return Instance ?: synchronized(this){
                Room.databaseBuilder(context, AirportDatabase::class.java, "vuelo_database")
                    .createFromAsset("database/flight_search.db" )
                    .build()
                    .also { Instance= it }
            }
        }





    }

}