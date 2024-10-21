package edu.unicauca.moneywise.ui



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.unicauca.moneywise.R

@Composable
fun Preferences() {

    Surface(
        modifier= Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        color= MaterialTheme.colorScheme.primaryContainer

    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 14.dp)
                .padding(top = 10.dp)
        ) {
            Text(
                text = "Soporte",
                style = MaterialTheme.typography.headlineLarge.copy(fontSize = 20.sp),
                onTextLayout = {},
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Tarjetas(
                Icon = R.drawable.ic_whatsapp,
                mainText = "Contactos",
            )

            Tarjetas(
                Icon = R.drawable.ic_feedback,
                mainText = "Feedback",
            )
            Tarjetas(
                Icon = R.drawable.ic_privacy_policy,
                mainText = "Politica de privacidad"
            )

            Tarjetas(
                Icon = R.drawable.ic_about,
                mainText = "Sobre nosotros",
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Tarjetas(
    Icon:Int,
    mainText:String,
) {
    Card(colors= CardDefaults.cardColors(
        containerColor= Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        modifier= Modifier
            .padding(top= 10.dp,bottom = 8.dp)
            .fillMaxWidth()
    ){
        Row(
            modifier= Modifier.padding(vertical=10.dp,horizontal=14.dp),
            verticalAlignment= Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment= Alignment.CenterVertically){
                Box(modifier = Modifier
                    .size(34.dp)
                    .clip(shape = RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.surface)){
                    Icon(
                        painter= painterResource(id = Icon),
                        contentDescription = null,
                        modifier= Modifier.padding(8.dp)
                    )
                }

                Spacer(modifier= Modifier.width(20.dp))

                Column {
                    Text(  text = mainText,
                        style = MaterialTheme.typography.headlineLarge.copy(fontSize = 16.sp),
                        onTextLayout = {},
                    )

                    /*   Text(  text = subText,
                           style = MaterialTheme.typography.labelMedium.copy(fontSize = 12.sp),
                           onTextLayout = {},
                       )*/
                }

            }

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                painter= painterResource(id = R.drawable.ic_right_arrow),
                contentDescription = null,
                modifier= Modifier.size(16.dp)

            )

        }
    }
}

@Preview
@Composable
private fun PreviewTarjetas() {

    Preferences()

}