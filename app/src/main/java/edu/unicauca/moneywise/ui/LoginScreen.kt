package edu.unicauca.moneywise.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation

import edu.unicauca.moneywise.R
import edu.unicauca.moneywise.ui.theme.Black
import edu.unicauca.moneywise.ui.theme.BlueGray
import edu.unicauca.moneywise.ui.theme.Roboto


@Composable
fun LoginScreen (onNextButtonClicked: () -> Unit = {} ) {
    // Estados para capturar el input del usuario
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    Surface{
        Column(modifier= Modifier.fillMaxSize()){
            TopSection()
            Spacer(modifier= Modifier.height(36.dp))


            Column (modifier= Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)){


                OutlinedTextField(
                    value = email.value,
                    onValueChange = { email.value = it },
                    label = { Text("Email") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp) // Reducir el padding inferior
                )

                Spacer(modifier= Modifier.height(15.dp))

                OutlinedTextField(
                    value = password.value,
                    onValueChange = { password.value = it },
                    label = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )


                Spacer(modifier= Modifier.height(15.dp))
                val context = LocalContext.current
                Button(
                    onClick = onNextButtonClicked
                    ,

                    modifier= Modifier
                        .fillMaxWidth()
                        .height(40.dp),

                    colors = ButtonDefaults.buttonColors(
                        containerColor = if(isSystemInDarkTheme()) BlueGray else Black,
                        contentColor = Color.White

                    ),
                    shape = RoundedCornerShape(size= 4.dp)
                ){
                    Text(text = "Ingresar",
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Medium),
                        onTextLayout = {})
                }

                val uiColor = if (isSystemInDarkTheme()) Color.White else Color.Black
                Box(
                    modifier = Modifier
                        .fillMaxHeight(fraction = 0.7F)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.BottomCenter
                ){
                    Text(text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color= Color(0xFF94A3B8),
                                fontSize = 14.sp,
                                fontFamily = Roboto,
                                fontWeight = FontWeight.Normal


                            )
                        ){
                            append("Olvidaste tu contraseña?")
                        }

                        withStyle(
                            style = SpanStyle(
                                color= uiColor,
                                fontSize = 14.sp,
                                fontFamily = Roboto,
                                fontWeight = FontWeight.Normal


                            )
                        ){
                            append(" ")
                            append("Recuperala")
                        }

                    })
                }


            }

        }

    }
}


@Composable
private fun TopSection() {
    val uiColor = if (isSystemInDarkTheme()) Color.White else Color.Black

    Box(
        contentAlignment = Alignment.TopCenter
    ) {

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.46f),
            painter = painterResource(id = R.drawable.shape),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )

        Column(
            modifier = Modifier.padding(top = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.headlineLarge.copy(fontSize = 35.sp),
                color = uiColor,
                onTextLayout ={}
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = stringResource(id = R.string.Money),
                style = MaterialTheme.typography.titleMedium,
                color = uiColor,
                onTextLayout ={}
            )


        }
        Text(
            modifier = Modifier
                .padding(bottom = 10.dp)
                .align(alignment = Alignment.BottomCenter),
            text = stringResource(id = R.string.inicio_sesion),
            style = MaterialTheme.typography.headlineLarge,
            color = uiColor,
            onTextLayout ={}
        )


    }
}


