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
fun EditMovScreen(
    movimiento: Movimiento?,
    onSave: (Movimiento) -> Unit,
    onCancel: () -> Unit,

) {
    // Estado para los campos de texto
    val fechaState = remember { mutableStateOf(movimiento!!.fecha) }
    val categoriaState = remember { mutableStateOf(movimiento!!.categoria) }
    val descripcionState = remember { mutableStateOf(movimiento!!.descripcion) }
    val montoState = remember { mutableStateOf(movimiento!!.monto) }

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
                    monto = montoState.value
                ))
            }) {
                Text("Guardar")
            }
            Button(onClick = onCancel) {
                Text("Cancelar")
            }
            Button(onClick = {
                // Eliminar el movimiento
                onSave(movimiento.copy(
                    fecha = fechaState.value,
                    categoria = categoriaState.value,
                    descripcion = descripcionState.value,
                    monto = montoState.value
                ))
            }

            ) {
                Text("Eliminar")
            }
        }
    }
}

