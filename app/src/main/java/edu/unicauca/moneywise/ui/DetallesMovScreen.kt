package edu.unicauca.moneywise.ui

import MoneyWiseViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun DetallesMovScreen(
    movimiento: Movimiento?,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            Button(onClick = onBackClick) {
                Text("Regresar")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(
                text = "Detalles del Movimiento",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Fecha: ${movimiento!!.fecha}",
                        style = MaterialTheme.typography.bodyLarge)
                    Text(text = "Categoría: ${movimiento.categoria}",
                        style = MaterialTheme.typography.bodyLarge)
                    Text(text = "Descripción: ${movimiento.descripcion}",
                        style = MaterialTheme.typography.bodyLarge)
                    Text(text = "Monto: ${movimiento.monto}",
                        style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
    }
}