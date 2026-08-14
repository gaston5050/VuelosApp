package com.example.vuelosapp.ui.screen.inicio

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.Color


import androidx.compose.material3.IconButton

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import com.example.vuelosapp.R
import com.example.vuelosapp.data.Favorite


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
            value= uiState.textoBusqueda,
            onValueChange = onTextoChanged,
            label = { Text("Ingrese nombre") },
            modifier = Modifier.fillMaxWidth()

        )
        LazyColumn( Modifier.fillMaxWidth()
        ) {
            if(uiState.textoBusqueda != "") {
                items(uiState.listadoAeropuertosFiltrados) { aeropuerto ->
                    AirportCard(aeropuerto, onClick = {onAeropuertoSelected(aeropuerto)} )

                }
            }
        }

    }

}

//Esta card me va a servir para listar los posibles destinos desde un aeropuerto seleccionado
// y tambien me va a servir para listar los Favoritos (filled star)

@Composable
fun FavoriteCard(favorite: Favorite){

    Card(
        onClick = {},
        Modifier.fillMaxWidth()
            .padding(0.dp,12.dp,0.dp, 0.dp)

    ) {
        Column(Modifier.padding(12.dp)
            .fillMaxWidth()) {
            // Set views in a column
            Row(){
                Column(Modifier.weight(1f)){
                    Text("Salida")
                    Spacer(Modifier.size(2.dp))
                    Text(favorite.departureCode, fontWeight = FontWeight.W700)
                    Spacer(Modifier.size(4.dp))
                    Text("Destino")
                    Spacer(Modifier.size(2.dp))
                    Text(favorite.destinationCode, fontWeight = FontWeight.W700)
                }
                IconButton(
                    onClick={}
                ){
                    Icon(painter = painterResource(R.drawable.baseline_star_border_24),
                        contentDescription = null)
                }



            }
        }
    }gi


}

@Composable
fun AirportCard(airport: Airport, onClick: ()-> Unit) {
    Card(
        onClick = onClick,
        Modifier.fillMaxWidth()
            .padding(0.dp,8.dp,0.dp, 0.dp)

    ) {
        Column(Modifier.padding(10.dp)
            .fillMaxWidth()) {
        // Set views in a column
        Row(){

        Text(airport.iataCode + " - ", fontWeight = FontWeight.W700)
        Text(airport.name )
        }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun favoritePreview (){
    val fav: Favorite = Favorite(0, "EZE - Ezeiza", "JPN - Japon")
    FavoriteCard(fav)


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



