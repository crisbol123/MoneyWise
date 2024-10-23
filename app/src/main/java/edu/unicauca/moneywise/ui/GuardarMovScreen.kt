package edu.unicauca.moneywise.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuardarMovScreen(
    onSave: (Movimiento) -> Unit,
    onCancel: () -> Unit
) {
    // Estado para los campos de texto
    val fechaState = remember { mutableStateOf("") }
    val categoriaState = remember { mutableStateOf("") }
    val descripcionState = remember { mutableStateOf("") }
    val montoState = remember { mutableStateOf("") }

    // Estado para el tipo de movimiento (Ingreso/Egreso)
    val tipoMovimientoState = remember { mutableStateOf("Ingreso") }  // Por defecto será "Ingreso"
    val expanded = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Guardar Movimiento")

        Spacer(modifier = Modifier.height(16.dp))

        // Campos de texto para ingresar el nuevo movimiento
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
            value = montoState.value,
            onValueChange = { montoState.value = it },
            label = { Text("Monto") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // ExposedDropdownMenuBox para seleccionar el tipo de movimiento con icono de flecha
        ExposedDropdownMenuBox(
            expanded = expanded.value,
            onExpandedChange = { expanded.value = !expanded.value }
        ) {
            TextField(
                value = tipoMovimientoState.value,
                onValueChange = {},
                readOnly = true,  // Para evitar que el usuario escriba manualmente
                label = { Text("Tipo de Movimiento") },
                trailingIcon = {
                    Icon(Icons.Filled.ArrowDropDown, contentDescription = "Dropdown")
                },
                modifier = Modifier.menuAnchor()  // Permite que el menú se ancle al TextField
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
                // Crear un nuevo movimiento y guardarlo
                val nuevoMovimiento = Movimiento(
                    id = 0, // Asumimos que el ID es asignado por el backend
                    fecha = fechaState.value,
                    categoria = categoriaState.value,
                    descripcion = descripcionState.value,
                    monto = montoState.value.toDouble(),
                    tipoMovimiento = tipoMovimientoState.value
                )


                onSave(nuevoMovimiento)
            }) {
                Text("Guardar")
            }
            Button(onClick = onCancel) {
                Text("Cancelar")
            }
        }
    }
}
