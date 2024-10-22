import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun SobreNosotrosScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF8FBC8F))
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo de Moneywise",
            modifier = Modifier
                .size(120.dp)
                .padding(bottom = 16.dp)
        )

        // Título
        Text(
            text = "Sobre Nosotros",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 24.dp)
        )


        Text(
            text = "Somos estudiantes de Ingeniería Electrónica y Telecomunicaciones de la Universidad del Cauca, énfasis en Telemática. Esta aplicación fue desarrollada como parte de la Electiva de Aplicaciones Móviles.",
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp)
        )


        CrearInfoCard(nombre = "Cristian Bolaños")
        Spacer(modifier = Modifier.height(16.dp))
        CrearInfoCard(nombre = "Jhon Diaz")
        Spacer(modifier = Modifier.height(16.dp))
        CrearInfoCard(nombre = "Ricardo Diaz")
    }
}

@Composable
fun CrearInfoCard(nombre: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(80.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF709B81)
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = nombre,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                ),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SobreNosotrosScreenPreview() {
    SobreNosotrosScreen()
}
