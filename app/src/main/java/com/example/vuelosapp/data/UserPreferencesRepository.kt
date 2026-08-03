package com.example.vuelosapp.data

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class UserPreferencesRepository(private val dataStore: DataStore<Preferences>) {

    companion object{
        private val TAG = "UserPreferencesRepo"
        private val TEXTO_INGRESADO = stringPreferencesKey("texto_ingresado")

    }

    val textIngresado: Flow<String> = dataStore.data
        .catch{
            if(it is IOException){

                Log.e(TAG, "Error leyendo las preferencias", it)
                emit(emptyPreferences())
            }
            else{
                throw it
            }

        }
        .map {
            preferences -> preferences[TEXTO_INGRESADO] ?: ""
        }

    suspend fun actualizarTextoIngresado(texto: String){

        dataStore.edit {
            preferences -> preferences[TEXTO_INGRESADO] = texto
        }
    }


}