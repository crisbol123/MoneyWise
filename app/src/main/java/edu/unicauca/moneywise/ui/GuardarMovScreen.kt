package edu.unicauca.moneywise.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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
                    monto = montoState.value
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
