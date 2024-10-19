/*package edu.unicauca.moneywise.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

data class Movimiento(
    val fecha: String,
    val categoria: String,
    val descripcion: String,
    val monto: String
)

@Composable
fun MovimientosScreen(
    movimientos: List<Movimiento>,
    onEditarClicked: () -> Unit,
    onAgregarClicked: () -> Unit,
    onDetallesClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Movimientos",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .background(Color(0xFF8BC34A))
                    .padding(16.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(bottom = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .background(Color(0xFFE0E0E0)),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Fecha", modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodyLarge)
                Text(text = "Categoría", modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodyLarge)
                Text(text = "Descripción", modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodyLarge)
                Text(text = "Monto", modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodyLarge)
            }

            Divider(color = Color.Gray, thickness = 1.dp)

            LazyColumn {
                items(movimientos) { movimiento ->
                    MovimientoRow(movimiento)
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { onEditarClicked() }) {
                Text("Editar")
            }
            Button(onClick = { onAgregarClicked() }) {
                Text("Agregar")
            }
            Button(onClick = { onDetallesClicked() }) {
                Text("Detalles")
            }
        }
    }
}

@Composable
fun MovimientoRow(movimiento: Movimiento) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color.White)
            .border(BorderStroke(1.dp, Color.Gray), shape = RoundedCornerShape(4.dp)),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = movimiento.fecha, modifier = Modifier.weight(1f))
        Text(text = movimiento.categoria, modifier = Modifier.weight(1f))
        Text(text = movimiento.descripcion, modifier = Modifier.weight(1f))
        Text(text = movimiento.monto, modifier = Modifier.weight(1f))
    }
}*/

package edu.unicauca.moneywise.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

data class Movimiento(
    val fecha: String,
    val categoria: String,
    val descripcion: String,
    val monto: String
)

@Composable
fun MovimientosScreen(
    movimientos: List<Movimiento>,
    onEditarClicked: (Movimiento) -> Unit,
    onAgregarClicked: () -> Unit,
    onDetallesClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Movimientos",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .background(Color(0xFF8BC34A))
                    .padding(16.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(bottom = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .background(Color(0xFFE0E0E0)),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Fecha", modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodyLarge)
                Text(text = "Categoría", modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodyLarge)
                Text(text = "Descripción", modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodyLarge)
                Text(text = "Monto", modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodyLarge)
            }

            Divider(color = Color.Gray, thickness = 1.dp)

            LazyColumn {
                items(movimientos) { movimiento ->
                    MovimientoRow(movimiento, onEditarClicked)
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { onAgregarClicked() }) {
                Text("Agregar")
            }
            Button(onClick = { onDetallesClicked() }) {
                Text("Detalles")
            }
        }
    }
}

@Composable
fun MovimientoRow(movimiento: Movimiento, onEditClicked: (Movimiento) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color.White)
            .border(BorderStroke(1.dp, Color.Gray), shape = RoundedCornerShape(4.dp)),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = movimiento.fecha, modifier = Modifier.weight(1f))
        Text(text = movimiento.categoria, modifier = Modifier.weight(1f))
        Text(text = movimiento.descripcion, modifier = Modifier.weight(1f))
        Text(text = movimiento.monto, modifier = Modifier.weight(1f))
        Button(onClick = { onEditClicked(movimiento) }) {
            Text("Editar")
        }
    }
}
