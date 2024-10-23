package edu.unicauca.moneywise.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditMovScreen(
    movimiento: Movimiento?,
    onSave: (Movimiento) -> Unit,
    onCancel: () -> Unit,
) {
    // Estado para el control del dropdown y los campos de texto
    val expanded = remember { mutableStateOf(false) }

    val fechaState = remember { mutableStateOf(movimiento!!.fecha) }
    val categoriaState = remember { mutableStateOf(movimiento!!.categoria) }
    val descripcionState = remember { mutableStateOf(movimiento!!.descripcion) }
    val montoState = remember { mutableStateOf(movimiento!!.monto) }
    val tipoMovimientoState = remember { mutableStateOf(movimiento!!.tipoMovimiento) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Editar Movimiento")

        Spacer(modifier = Modifier.height(16.dp))

        // Campos de texto para editar
        TextField(
            value = fechaState.value,
            onValueChange = { fechaState.value = it },
            label = { Text("Fecha") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = categoriaState.value,
            onValueChange = { categoriaState.value = it },
            label = { Text("Categoría") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = descripcionState.value,
            onValueChange = { descripcionState.value = it },
            label = { Text("Descripción") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = montoState.value.toString(),
            onValueChange = { montoState.value = it.toDouble() },
            label = { Text("Monto") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        ExposedDropdownMenuBox(
            expanded = expanded.value,
            onExpandedChange = { expanded.value = !expanded.value }
        ) {
            TextField(
                value = tipoMovimientoState.value,
                onValueChange = {},
                readOnly = true,
                label = { Text("Tipo de Movimiento") },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = "Desplegar menú"
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Ingreso") },
                    onClick = {
                        tipoMovimientoState.value = "Ingreso"
                        expanded.value = false
                    }
                )
                DropdownMenuItem(
                    text = { Text("Egreso") },
                    onClick = {
                        tipoMovimientoState.value = "Egreso"
                        expanded.value = false
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {
                // Guardar el movimiento actualizado
                onSave(movimiento!!.copy(
                    fecha = fechaState.value,
                    categoria = categoriaState.value,
                    descripcion = descripcionState.value,
                    monto = montoState.value,
                    tipoMovimiento = tipoMovimientoState.value // Guardar el tipo de movimiento
                ))
            }) {
                Text("Guardar")
            }
            Button(onClick = onCancel) {
                Text("Cancelar")
            }
        }
    }
}
