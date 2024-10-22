package edu.unicauca.moneywise.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ContactoScreen() {
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
            text = "Contacto",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Información de contacto
        ContactCard(
            nombre = "Cristian Bolaños",
            email = "cristianfbolanos@unicauca.edu.co",
            telefono = "3175001488"
        )
        Spacer(modifier = Modifier.height(16.dp))
        ContactCard(
            nombre = "Jhon Diaz",
            email = "jhonsdiaz@unicauca.edu.co",
            telefono = "3128273902"
        )
        Spacer(modifier = Modifier.height(16.dp))
        ContactCard(
            nombre = "Ricardo Diaz",
            email = "radiaz@unicauca.edu.co",
            telefono = "3023283249"
        )
    }
}

@Composable
fun ContactCard(nombre: String, email: String, telefono: String) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .clickable { expanded = !expanded }
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF709B81)
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = nombre,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                ),
                textAlign = TextAlign.Center
            )


            AnimatedVisibility(
                visible = expanded,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Column(
                    modifier = Modifier.padding(top = 8.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Correo: $email",
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp)
                    )
                    Text(
                        text = "Teléfono: $telefono",
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContactoScreenPreview() {
    ContactoScreen()
}
