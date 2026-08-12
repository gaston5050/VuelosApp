package com.example.vuelosapp.ui.screen.inicio

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vuelosapp.data.Airport
import com.example.vuelosapp.data.VuelosUiState
import com.example.vuelosapp.ui.AppViewModelProvider
import com.example.vuelosapp.ui.theme.VuelosAppTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.TextField

// 1. PANTALLA CON ESTADO: Se usa en la navegación de la App
@Composable
fun InicioScreen(
    viewModel: InicioViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState by viewModel.uiState.collectAsState()

    InicioContent(
        uiState = uiState,
        onTextoChanged = { texto -> viewModel.caracteresIngresados(texto) },
        onAeropuertoSelected = { aeropuerto -> viewModel.aeropuertoSeleccionado(aeropuerto) }
    )
}

// 2. CONTENIDO SIN ESTADO: Dibuja la UI pura (Esta es la que vas a probar en la Preview)
@Composable
fun InicioContent(
    uiState: VuelosUiState,
    onTextoChanged: (String) -> Unit,
    onAeropuertoSelected: (Airport) -> Unit
) {
    // Acá va todo tu diseño visual (TextField, LazyColumn, etc.)
    // Usás 'uiState.textoBusqueda', 'uiState.listadoAeropuertosFiltrados', etc.

    val padding = 16.dp
    Column(Modifier
        .fillMaxWidth()
        .padding(padding, top= 40.dp,end= padding)
    ){
        TextField(
            value= "",
            onValueChange = onTextoChanged,
            label = { Text("Ingrese nombre") },
            modifier = Modifier.fillMaxWidth()

        )
        LazyColumn( modifier = Modifier.padding(12.dp)
            .fillMaxWidth()
        ) {


            items(uiState.listadoAeropuertosFiltrados) {
                    aeropuerto -> AirportCard(aeropuerto)

            }
        }

    }

}

@Composable
fun AirportCard(airport: Airport) {
    Card(
        onClick={},
        Modifier.fillMaxWidth()){
        Text("Coso")
    }
}



// 3. PREVIEW: Apunta a InicioContent y le pasa datos falsos de prueba (Mock Data)

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun InicioScreenPreview() {
    VuelosAppTheme {
        InicioContent(
            uiState = VuelosUiState(
                textoBusqueda = "EZE",
                listadoAeropuertosFiltrados = listOf(
                    Airport(id = 1, name = "Ezeiza", iataCode = "EZE", passengers = 100000),
                    Airport(id = 2, name = "Aeroparque", iataCode = "AEP", passengers = 80000)
                )
            ),
            onTextoChanged = {},
            onAeropuertoSelected = {}
        )
    }
}



