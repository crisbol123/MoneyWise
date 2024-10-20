
package edu.unicauca.moneywise

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import edu.unicauca.moneywise.ui.EditMovScreen
import edu.unicauca.moneywise.ui.LoginScreen
import edu.unicauca.moneywise.ui.MoneyWiseViewModel
import edu.unicauca.moneywise.ui.Movimiento
import edu.unicauca.moneywise.ui.MovimientosScreen
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

enum class MoneyWiseScreen(val route: String) {
    Login("login"),
    Luna("NewLogin"),
    Home("home"),
    Movimientos("movimientos"),
    Profile("profile"),
    EditMov("edit_mov")
}

fun encodeUrlParam(param: String): String {
    return URLEncoder.encode(param, StandardCharsets.UTF_8.toString())
}

@Composable
fun MoneyWiseBottomNavigation(navController: NavHostController) {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = false,
            onClick = { navController.navigate(MoneyWiseScreen.Home.route) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.List, contentDescription = "Movimientos") },
            label = { Text("Movimientos") },
            selected = false,
            onClick = { navController.navigate(MoneyWiseScreen.Movimientos.route) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Person, contentDescription = "Profile") },
            label = { Text("Profile") },
            selected = false,
            onClick = { navController.navigate(MoneyWiseScreen.Profile.route) }
        )
    }
}

@Composable
fun MoneyWiseApp(
    viewModel: MoneyWiseViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        bottomBar = { if (currentRoute != MoneyWiseScreen.Login.route) {
            MoneyWiseBottomNavigation(navController)
        }}
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MoneyWiseScreen.Login.route,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(MoneyWiseScreen.Login.route) {
                LoginScreen(onNextButtonClicked = {
                    navController.navigate(MoneyWiseScreen.Home.route)
                })
            }

            composable(MoneyWiseScreen.Home.route) {
                Text("Home")
            }

            composable(MoneyWiseScreen.Movimientos.route) {
                MovimientosScreen(
                    movimientos = viewModel.movimientos.toList(), // Usar la lista del ViewModel
                    onEditarClicked = { movimiento ->
                        val encodedFecha = encodeUrlParam(movimiento.fecha.replace("/", "-"))
                        val encodedCategoria = encodeUrlParam(movimiento.categoria)
                        val encodedDescripcion = encodeUrlParam(movimiento.descripcion)
                        val encodedMonto = encodeUrlParam(movimiento.monto)

                        navController.navigate(MoneyWiseScreen.EditMov.route + "/$encodedFecha/$encodedCategoria/$encodedDescripcion/$encodedMonto")
                    },
                    onAgregarClicked = { /* Manejar agregar */ },

                )
            }

            composable(MoneyWiseScreen.EditMov.route + "/{fecha}/{categoria}/{descripcion}/{monto}") { backStackEntry ->
                val fecha = backStackEntry.arguments?.getString("fecha")?.replace("-", "/") ?: ""
                val categoria = backStackEntry.arguments?.getString("categoria") ?: ""
                val descripcion = backStackEntry.arguments?.getString("descripcion") ?: ""
                val monto = backStackEntry.arguments?.getString("monto") ?: ""

                val movimiento = Movimiento(fecha, categoria, descripcion, monto)

                EditMovScreen(
                    movimiento = movimiento,
                    onSave = { updatedMovimiento ->
                        viewModel.updateMovimiento(movimiento, updatedMovimiento) // Actualizar movimiento en el ViewModel
                        navController.navigate(MoneyWiseScreen.Movimientos.route) {
                            popUpTo(MoneyWiseScreen.Movimientos.route) { inclusive = true }
                        }
                    },
                    onCancel = {
                        navController.navigate(MoneyWiseScreen.Movimientos.route)
                    }
                )
            }
        }
    }
}
