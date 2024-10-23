package edu.unicauca.moneywise.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.unicauca.moneywise.R
import edu.unicauca.moneywise.Usuario

@Composable
fun ConfigurationScreen(usuario: Usuario, logout: () -> Unit = {}) {
    val imageUri = rememberSaveable { mutableStateOf("") }

    Surface(
        color = MaterialTheme.colorScheme.primaryContainer
    ) {
        Column(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                border = BorderStroke(2.dp, MaterialTheme.colorScheme.surface),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp
                ),
                shape = CircleShape,
                modifier = Modifier
                    .padding(8.dp)
                    .size(100.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_user),
                    contentDescription = null,
                    modifier = Modifier
                        .wrapContentSize()
                        .clickable { },
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(Modifier.height(8.dp))
            Text(
                text = usuario.nombre,
                style = MaterialTheme.typography.labelMedium.copy(fontSize = 20.sp),
                onTextLayout = {}
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = usuario.correo,
                style = MaterialTheme.typography.labelMedium.copy(fontSize = 20.sp),
                onTextLayout = {}
            )
            Spacer(Modifier.height(16.dp))
            Button(onClick = logout) {
                Text("Cerrar Sesión")
            }
            Spacer(Modifier.height(8.dp))
        }
    }
}

@Composable
fun CompleteScreen(usuario: Usuario, navegar: (String) -> Unit = {}, onLogout: () -> Unit = {}) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.primaryContainer
    ) {
        Column {
            ConfigurationScreen(usuario, logout = { onLogout() })
            Preferences(onNavigate = { navegar(it) })
        }
    }
}

@Preview
@Composable
private fun PreviewConfigurationScreen() {
    CompleteScreen(Usuario("", "", "", "", ""))
}
