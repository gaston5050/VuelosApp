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
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class InicioViewModel(
    private val flightRepository: VuelosRepository,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(VuelosUiState())
    val uiState: StateFlow<VuelosUiState> = _uiState.asStateFlow()

    private val _textoBusqueda = MutableStateFlow("")

    init {
        // 1. Carga inicial: recupera el texto previo guardado en DataStore
        viewModelScope.launch {
            val textoGuardado = userPreferencesRepository.textIngresado.first()
            _uiState.update { it.copy(textoBusqueda = textoGuardado) }
            _textoBusqueda.value = textoGuardado
        }

        // 2. Room busca aeropuertos y SOLO actualiza la lista (no toca el texto)
        viewModelScope.launch {
            _textoBusqueda
                .flatMapLatest { textEscrito ->
                    flightRepository.getAirports(textEscrito)
                }
                .collect { lista ->
                    _uiState.update { estadoActual ->
                        estadoActual.copy(listadoAeropuertosFiltrados = lista)
                    }
                }
        }

        // 3. Escucha favoritos en tiempo real
        viewModelScope.launch {
            flightRepository.getFavorites().collect { favoritos ->
                _uiState.update { estadoActual ->
                    estadoActual.copy(listaFavoritos = favoritos)
                }
            }
        }
    }

    fun caracteresIngresados(caracter: String) {
        // A. Actualización síncrona instantánea en la UI (el cursor nunca más se traba)
        _uiState.update { it.copy(textoBusqueda = caracter) }

        // B. Notificamos al canal para que Room filtre la lista
        _textoBusqueda.value = caracter

        // C. Persistencia silenciosa en disco
        viewModelScope.launch {
            userPreferencesRepository.actualizarTextoIngresado(caracter)
        }
    }

    fun aeropuertoSeleccionado(aeropuerto: Airport) {
        _uiState.update { estadoActual ->
            estadoActual.copy(aeropuertoSeleccionado = aeropuerto)
        }
    }
}