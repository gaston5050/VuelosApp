package com.example.vuelosapp.data

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Airport::class, Favorite::class], version = 1, exportSchema = false )
abstract class AirportDatabase: RoomDatabase() {

    //
    abstract fun AirportDao(): AirportDao

}