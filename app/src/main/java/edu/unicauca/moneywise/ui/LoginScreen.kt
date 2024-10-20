package edu.unicauca.moneywise.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import android.util.Log
import androidx.compose.runtime.rememberCoroutineScope
import edu.unicauca.moneywise.AuthService
import edu.unicauca.moneywise.LoginRequest
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun LoginScreen(onLoginSuccess: (String) -> Unit) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = email.value,
            onValueChange = { email.value = it },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        Button(
            onClick = {
                val retrofit = Retrofit.Builder()
                    .baseUrl("http://192.168.1.40:8090/usuarios/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                val authService = retrofit.create(AuthService::class.java)
                val request = LoginRequest(email.value, password.value)

                coroutineScope.launch {
                    try {
                        val response = authService.login(request).execute()
                        if (response.isSuccessful) {
                            val token = response.body()?.token
                            if (token != null) {
                                onLoginSuccess(token)
                                // Aquí puedes navegar a la siguiente pantalla
                            }
                        } else {
                            Log.e("LoginError", "Error: ${response.message()}")
                        }
                    } catch (e: Exception) {
                        Log.e("LoginError", "Exception: ${e.message}")
                    }
                }
            },
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
        ) {
            Text("Login")
        }
    }
}


