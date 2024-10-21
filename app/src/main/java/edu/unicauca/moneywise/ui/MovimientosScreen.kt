package edu.unicauca.moneywise.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.unicauca.moneywise.R
import edu.unicauca.moneywise.MoneyWiseViewModel

data class Movimiento(
    val id: Long,
    val fecha: String,
    val categoria: String,
    val descripcion: String,
    val monto: String
)

@Composable
fun MovimientosScreen(
    viewModel: MoneyWiseViewModel,
    onEditarClicked: (Movimiento) -> Unit,
    onAgregarClicked: () -> Unit,
    onDetallesClicked: (Movimiento) -> Unit
) {
    // Obtenemos los movimientos desde el ViewModel
    val movimientos by viewModel.movimientos.collectAsState(initial = emptyList())

    var movimientoSeleccionado by remember { mutableStateOf<Movimiento?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Movimientos",
                color = Color.White,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .background(colorResource(id = R.color.green))
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
                    .background(Color(0xFFE0E0E0))
                    .border(BorderStroke(1.dp, Color.Gray)),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                EncabezadoCelda("Fecha", Modifier.weight(1f))
                EncabezadoCelda("Descripción", Modifier.weight(2f))
                EncabezadoCelda("Monto", Modifier.weight(1f))
            }

            Divider(color = Color.Gray, thickness = 1.dp)

            LazyColumn {
                items(movimientos) { movimiento ->
                    val esSeleccionado = movimiento == movimientoSeleccionado
                    MovimientoRow(
                        movimiento = movimiento,
                        esSeleccionado = esSeleccionado,
                        onClick = { movimientoSeleccionado = movimiento }
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { onAgregarClicked() }, colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.green),
                contentColor = Color.White
            )) {
                Text("Agregar")
            }
            Button(
                onClick = { movimientoSeleccionado?.let { onEditarClicked(it) } },
                enabled = movimientoSeleccionado != null,
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.green),
                    contentColor = Color.White
                )
            ) {
                Text("Editar")
            }
            Button(
                onClick = { movimientoSeleccionado?.let { onDetallesClicked(it) } },
                enabled = movimientoSeleccionado != null,
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.green),
                    contentColor = Color.White
                )
            ) {
                Text("Detalles")
            }
        }
    }
}

@Composable
fun EncabezadoCelda(texto: String, modifier: Modifier = Modifier) {
    Text(
        text = texto,
        fontSize = 12.sp,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodyLarge,
        modifier = modifier
            .padding(8.dp)
            .border(BorderStroke(1.dp, Color.Gray))
            .background(Color.LightGray)
            .padding(8.dp)
    )
}

@Composable
fun MovimientoRow(movimiento: Movimiento, esSeleccionado: Boolean, onClick: () -> Unit) {
    val backgroundColor = if (esSeleccionado) Color(0xFF8BC34A) else Color.White

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 0.dp)
            .background(backgroundColor)
            .border(BorderStroke(1.dp, Color.Gray), shape = RoundedCornerShape(4.dp))
            .padding(2.dp)
            .clickable { onClick() },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        CeldaTexto(movimiento.fecha, Modifier.weight(1f))
        CeldaTexto(movimiento.descripcion, Modifier.weight(2f))
        CeldaTexto(movimiento.monto, Modifier.weight(1f))
    }
}

@Composable
fun CeldaTexto(texto: String, modifier: Modifier = Modifier) {
    Text(
        text = texto,
        fontSize = 10.sp,
        textAlign = TextAlign.Center,
        modifier = modifier
            .padding(8.dp)
            .border(BorderStroke(1.dp, Color.Gray))
            .padding(2.dp)
    )
}

@Preview
@Composable
fun MovimientosScreenPreview() {
    val movimientosEjemplo = listOf(

        Movimiento(1, "19/08/2024", "Supermercado", "$25000"),
        Movimiento(2, "18/08/2024", "Gasolina", "$50000"),
        Movimiento(3, "18/08/2024", "Cine", "$12000"),
        Movimiento(4, "14/08/2024", "Mensual", "$200000")

    )

    MovimientosScreen(
        viewModel = MoneyWiseViewModel(), // Reemplaza con una instancia válida en tu código real
        onEditarClicked = {},
        onAgregarClicked = {},
        onDetallesClicked = {}
    )
}
