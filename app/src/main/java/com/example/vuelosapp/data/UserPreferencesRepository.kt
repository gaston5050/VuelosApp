package com.example.vuelosapp.data

import android.content.Context
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
import androidx.datastore.preferences.preferencesDataStore

//OJO ESTO VA A FUERA DE LA CLASS
// Defino el nombre que va a tener el archivo de preferencias dentro del disco del telefono
private const val archivo_preferencias = "preferencias_volatiles"
//Aca le agrego una propiedad "virtual" a la clase Context (que seria .dataStore)
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(archivo_preferencias)


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
