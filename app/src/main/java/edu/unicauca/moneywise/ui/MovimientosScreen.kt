package edu.unicauca.moneywise.ui

/*import androidx.compose.foundation.BorderStroke
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.material3.TextField


data class Movimiento(
    val fecha: String,
    val categoria: String,
    val descripcion: String,
    val monto: String
)

@Composable
fun MovimientosScreen(
    movimientos: List<Movimiento>,
    onMovimientoUpdated: (Movimiento) -> Unit,
    onAgregarClicked: () -> Unit,
    onDetallesClicked: () -> Unit
) {
    // Estado para controlar qué movimiento está siendo editado
    var movimientoEditado by remember { mutableStateOf<Movimiento?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Si hay un movimiento en edición, mostramos la pantalla de edición
        movimientoEditado?.let { movimiento ->
            MovimientoEditScreen(
                movimiento = movimiento,
                onSave = { nuevoMovimiento ->
                    movimientoEditado = null  // Salir del modo edición
                    onMovimientoUpdated(nuevoMovimiento)  // Pasar el movimiento actualizado
                },
                onCancel = {
                    movimientoEditado = null  // Salir del modo edición sin cambios
                }
            )
        } ?: run {
            // Si no hay movimiento en edición, mostrar la lista de movimientos
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

            LazyColumn {
                items(movimientos) { movimiento ->
                    MovimientoRow(movimiento = movimiento)
                    // Botón para activar la edición de este movimiento
                    Button(onClick = { movimientoEditado = movimiento }) {
                        Text("Editar")
                    }
                }
            }

            // Botones de agregar y detalles
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
}

@Composable
fun MovimientoEditScreen(
    movimiento: Movimiento,
    onSave: (Movimiento) -> Unit,
    onCancel: () -> Unit
) {
    // Estados para los campos de edición
    var categoria by remember { mutableStateOf(movimiento.categoria) }
    var descripcion by remember { mutableStateOf(movimiento.descripcion) }
    var monto by remember { mutableStateOf(movimiento.monto) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Editar Movimiento",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(vertical = 16.dp)
                .background(Color(0xFF8BC34A))
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de edición para la categoría
        TextField(
            value = categoria,
            onValueChange = { categoria = it },
            label = { Text("Categoría") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo de edición para la descripción
        TextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo de edición para el monto
        TextField(
            value = monto,
            onValueChange = { monto = it },
            label = { Text("Monto") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botones para Guardar o Cancelar la edición
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { onCancel() }) {
                Text("Cancelar")
            }
            Button(onClick = {
                // Crear un nuevo objeto Movimiento con los cambios realizados
                val nuevoMovimiento = movimiento.copy(
                    categoria = categoria,
                    descripcion = descripcion,
                    monto = monto
                )
                onSave(nuevoMovimiento)
            }) {
                Text("Guardar")
            }
        }
    }
}*/
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
    onMovimientoUpdated: (Movimiento) -> Unit,
    onAgregarClicked: () -> Unit,
    onDetallesClicked: () -> Unit
) {
    // Estado para controlar qué movimiento está siendo editado
    var movimientoEditado by remember { mutableStateOf<Movimiento?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Si hay un movimiento en edición, mostramos la pantalla de edición
        movimientoEditado?.let { movimiento ->
            MovimientoEditScreen(
                movimiento = movimiento,
                onSave = { nuevoMovimiento ->
                    movimientoEditado = null  // Salir del modo edición
                    onMovimientoUpdated(nuevoMovimiento)  // Pasar el movimiento actualizado
                },
                onCancel = {
                    movimientoEditado = null  // Salir del modo edición sin cambios
                }
            )
        } ?: run {
            // Si no hay movimiento en edición, mostrar la lista de movimientos
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

            LazyColumn {
                items(movimientos) { movimiento ->
                    MovimientoRow(movimiento = movimiento)
                    // Botón para activar la edición de este movimiento
                    Button(onClick = { movimientoEditado = movimiento }) {
                        Text("Editar")
                    }
                }
            }

            // Botones de agregar y detalles
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
}

@Composable
fun MovimientoEditScreen(
    movimiento: Movimiento,
    onSave: (Movimiento) -> Unit,
    onCancel: () -> Unit
) {
    // Estados para los campos de edición
    var categoria by remember { mutableStateOf(movimiento.categoria) }
    var descripcion by remember { mutableStateOf(movimiento.descripcion) }
    var monto by remember { mutableStateOf(movimiento.monto) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Editar Movimiento",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(vertical = 16.dp)
                .background(Color(0xFF8BC34A))
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de edición para la categoría
        TextField(
            value = categoria,
            onValueChange = { categoria = it },
            label = { Text("Categoría") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo de edición para la descripción
        TextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo de edición para el monto
        TextField(
            value = monto,
            onValueChange = { monto = it },
            label = { Text("Monto") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botones para Guardar o Cancelar la edición
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { onCancel() }) {
                Text("Cancelar")
            }
            Button(onClick = {
                // Crear un nuevo objeto Movimiento con los cambios realizados
                val nuevoMovimiento = movimiento.copy(
                    categoria = categoria,
                    descripcion = descripcion,
                    monto = monto
                )
                onSave(nuevoMovimiento)
            }) {
                Text("Guardar")
            }
        }
    }
}

