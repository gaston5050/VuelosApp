package com.example.vuelosapp.data

import android.content.Context
import androidx.datastore.dataStoreFile

class VuelosAppContainer(private val context: Context): AppContainer {


    override val vuelosRepository: VuelosRepository by lazy { OfflineVuelosRepository( AirportDatabase.getDatabase(context).airportDao()) }

    override val userPreferencesRepository: UserPreferencesRepository by lazy { UserPreferencesRepository(context.dataStore) }

    }

