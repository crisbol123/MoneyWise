import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.unicauca.moneywise.R

@Composable
fun BalanceScreen() {
    // Almacena el valor de presupuesto
    var estadoDeCuenta by remember { mutableStateOf(0.00) }
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF8FBC8F))
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título
        Text(
            text = "MoneyWise",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 8.dp) // Espacio debajo del título
        )

        // logo
        Image(
            painter = painterResource(id = R.drawable.logo), // Asegúrate de que el nombre coincida con tu archivo
            contentDescription = "Logo MoneyWise",
            modifier = Modifier
                .height(150.dp) // Aumenta la altura del logo
                .padding(bottom = 24.dp)
        )

        // Estado de cuenta
        InfoCard(title = "Estado de cuenta", amount = "$ ${String.format("%.2f", estadoDeCuenta)}")
        Spacer(modifier = Modifier.height(16.dp)) // Espacio entre las tarjetas
        // Dinero gastado
        InfoCard(title = "Dinero gastado", amount = "$ 0.00")
        Spacer(modifier = Modifier.height(16.dp)) // Espacio entre las tarjetas
        // Balance total
        InfoCard(title = "Balance total", amount = "$ 0.00")

        Spacer(modifier = Modifier.height(24.dp))

        // Agregar presupuesto
        Button(onClick = { showDialog = true }) {
            Text("Agregar Presupuesto")
        }


        if (showDialog) {
            AddBudgetDialog(
                onDismiss = { showDialog = false },
                onConfirm = { presupuesto ->
                    estadoDeCuenta += presupuesto // Actualiza el estado de cuenta
                    showDialog = false // Cierra el diálogo
                }
            )
        }
    }
}

@Composable
fun AddBudgetDialog(onDismiss: () -> Unit, onConfirm: (Double) -> Unit) {
    var presupuesto by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Agregar Presupuesto") },
        text = {
            Column {
                Text("Ingrese el monto del presupuesto:")
                TextField(
                    value = presupuesto,
                    onValueChange = { presupuesto = it },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val presupuestoDouble = presupuesto.toDoubleOrNull() ?: 0.0
                    onConfirm(presupuestoDouble)
                }
            ) {
                Text("Agregar")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}

@Composable
fun InfoCard(title: String, amount: String) {

    Card(
        modifier = Modifier
            .fillMaxWidth(0.8f) // Anchura de la tarjeta
            .height(100.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors( // Definimos los colores de la tarjeta
            containerColor = Color(0xFF99FF99) // Fondo de la tarjeta verde claro
        ),
        elevation = CardDefaults.cardElevation(4.dp) // Elevación de la tarjeta
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 18.sp),
                textAlign = TextAlign.Center
            )
            Text(
                text = amount,
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 24.sp),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BalanceScreenPreview() {
    BalanceScreen()
}
