package edu.unicauca.moneywise.ui

import MoneyWiseViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import edu.unicauca.moneywise.MoneyWiseViewModel

@Composable
fun DetallesMovScreen(
    movimientoId: Int,  // Ahora recibimos el ID del movimiento
    viewModel: MoneyWiseViewModel,
    onBackClick: () -> Unit
) {
    // Cargar los detalles del movimiento por su ID desde el ViewModel
    val movimiento = viewModel.getMovimientoById(movimientoId)

    // Usamos LaunchedEffect para asegurarnos de que se carguen los datos al iniciar la pantalla
    LaunchedEffect(movimientoId) {
        viewModel.loadMovimientoById(movimientoId)
    }

    Scaffold(
        topBar = {
            Button(onClick = onBackClick) {
                Text("Regresar")
            }
        }
    ) { paddingValues ->
        movimiento?.let { mov ->
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
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "Fecha: ${mov.fecha}", style = MaterialTheme.typography.bodyLarge)
                        Text(text = "Categoría: ${mov.categoria}", style = MaterialTheme.typography.bodyLarge)
                        Text(text = "Descripción: ${mov.descripcion}", style = MaterialTheme.typography.bodyLarge)
                        Text(text = "Monto: ${mov.monto}", style = MaterialTheme.typography.bodyLarge)
                    }
                }
            }
        } ?: run {
            Text(text = "Cargando...", style = MaterialTheme.typography.bodyLarge)
        }
    }
}
