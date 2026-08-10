package com.example.vuelosapp.ui.screen.inicio

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vuelosapp.data.Airport
import com.example.vuelosapp.data.UserPreferencesRepository
import com.example.vuelosapp.data.VuelosRepository
import com.example.vuelosapp.data.VuelosUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class InicioViewModel(private val flightRepository: VuelosRepository, private val userPreferencesRepository: UserPreferencesRepository): ViewModel() {


    //uiState real la que no es accesible desde afuera
    private val _uiState = MutableStateFlow(VuelosUiState())
    //uiState que se puede modoficar para actualizar la original
    val uiState: StateFlow<VuelosUiState> = _uiState.asStateFlow()


    init {
        viewModelScope.launch {


            // escuha dataStore y busca los aeropuertos
                userPreferencesRepository.textIngresado.collect {
                    textEscrito ->
                    flightRepository.getAirports(textEscrito).collect {
                        lista -> _uiState.update {
                            estadoActual -> estadoActual.copy(
                                textoBusqueda =  textEscrito,
                                listadoAeropuertosFiltrados = lista
                            )
                    }
                    }
                }


        }
        //Escucha los favoritos en el momento

        viewModelScope.launch{
            flightRepository.getFavorites().collect { favoritos ->
                _uiState.update {
                        estadoActual ->
                    estadoActual.copy(listaFavoritos = favoritos)
                }
            }
        }
    }

    fun caracteresIngresados(caracter: String){
        viewModelScope.launch {
        userPreferencesRepository.actualizarTextoIngresado(caracter)
        }
    }

    fun aeropuertoSeleccionado(aeropuerto: Airport){

            _uiState.update {
                aeropuertoSeleccionado->
                aeropuertoSeleccionado.copy(aeropuertoSeleccionado = aeropuerto)

        }
    }



}