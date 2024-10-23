
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
    val dayState = remember { mutableStateOf("") }
    val monthState = remember { mutableStateOf("") }
    val yearState = remember { mutableStateOf("") }
    val categoriaState = remember { mutableStateOf("") }
    val descripcionState = remember { mutableStateOf("") }
    val montoState = remember { mutableStateOf("") }
    val tipoMovimientoState = remember { mutableStateOf("Ingreso") }
    val expanded = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Guardar Movimiento")

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TextField(
                value = dayState.value,
                onValueChange = { dayState.value = it },
                label = { Text("Día") },
                modifier = Modifier.weight(1f)
            )
            TextField(
                value = monthState.value,
                onValueChange = { monthState.value = it },
                label = { Text("Mes") },
                modifier = Modifier.weight(1f)
            )
            TextField(
                value = yearState.value,
                onValueChange = { yearState.value = it },
                label = { Text("Año") },
                modifier = Modifier.weight(1f)
            )
        }

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
                    Icon(Icons.Filled.ArrowDropDown, contentDescription = "Dropdown")
                },
                modifier = Modifier.menuAnchor()
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
                val formattedDate = "${dayState.value.padStart(2, '0')}/${monthState.value.padStart(2, '0')}/${yearState.value}"

                val nuevoMovimiento = Movimiento(
                    id = 0,
                    fecha = formattedDate,
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
