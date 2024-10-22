package edu.unicauca.moneywise.ui



import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
fun ConfigurationScreen( usuario: Usuario) {
    val imageUri= rememberSaveable { mutableStateOf("") }


    Surface(
        color= MaterialTheme.colorScheme.primaryContainer
    ){
        Column(
            modifier= Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalAlignment= Alignment.CenterHorizontally
        ){
            Card(

                border = BorderStroke(2.dp, MaterialTheme.colorScheme.surface),
                colors= CardDefaults.cardColors(
                    containerColor= MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp
                ),
                shape= CircleShape,
                modifier= Modifier
                    .padding(8.dp)
                    .size(100.dp)
            ){
                Image(
                    painter= painterResource(id = R.drawable.ic_user),
                    contentDescription= null,
                    modifier= Modifier
                        .wrapContentSize()
                        .clickable { },
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(Modifier.height(8.dp))
            Text(text = usuario.nombre,
                style = MaterialTheme.typography.labelMedium.copy(fontSize = 20.sp),
                onTextLayout = {})
            Spacer(Modifier.height(8.dp))
            Text(text = usuario.correo,
                style = MaterialTheme.typography.labelMedium.copy(fontSize = 20.sp),
                onTextLayout = {}
            )
            Spacer(Modifier.height(8.dp))
        }
    }
}


@Composable
fun CompleteScreen( usuario: Usuario) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color= MaterialTheme.colorScheme.primaryContainer
    ){
        Column() {
            ConfigurationScreen(usuario)
            Preferences()
        }
    }

}



@Preview
@Composable
private fun PreviewConfigurationScreen() {

    CompleteScreen( Usuario("", "", "", "", ""))

}