
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import edu.unicauca.moneywise.ui.EditMovScreen
import edu.unicauca.moneywise.ui.LoginScreen
import edu.unicauca.moneywise.ui.Movimiento
import edu.unicauca.moneywise.ui.MovimientosScreen
import edu.unicauca.moneywise.ui.CompleteScreen
import edu.unicauca.moneywise.ui.DetallesMovScreen
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

enum class MoneyWiseScreen(val route: String) {
    Login("login"),
    Luna("NewLogin"),
    Home("home"),
    Movimientos("movimientos"),
    Profile("profile"),
    EditMov("edit_mov"),
    DetallesMov("detalles_mov")
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
    var authToken by remember { mutableStateOf("") }

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
                LoginScreen(onLoginSuccess = { token ->
                    authToken = token
                    navController.navigate(MoneyWiseScreen.Home.route)
                } )
            }

            composable(MoneyWiseScreen.Home.route) {
                Text("Home")
            }

            composable(MoneyWiseScreen.Movimientos.route) {
                MovimientosScreen(
                    movimientos = viewModel.movimientos.toList(),
                    onEditarClicked = { movimiento ->
                        val encodedFecha = encodeUrlParam(movimiento.fecha.replace("/", "-"))
                        val encodedCategoria = encodeUrlParam(movimiento.categoria)
                        val encodedDescripcion = encodeUrlParam(movimiento.descripcion)
                        val encodedMonto = encodeUrlParam(movimiento.monto)

                        navController.navigate(MoneyWiseScreen.EditMov.route + "/$encodedFecha/$encodedCategoria/$encodedDescripcion/$encodedMonto")
                    },
                    onAgregarClicked = { /* Manejar agregar */ },
                    onDetallesClicked = { movimiento ->
                        val encodedFecha = encodeUrlParam(movimiento.fecha.replace("/", "-"))
                        val encodedCategoria = encodeUrlParam(movimiento.categoria)
                        val encodedDescripcion = encodeUrlParam(movimiento.descripcion)
                        val encodedMonto = encodeUrlParam(movimiento.monto)

                        navController.navigate(MoneyWiseScreen.DetallesMov.route + "/$encodedFecha/$encodedCategoria/$encodedDescripcion/$encodedMonto")
                    }
                )
            }

            composable(MoneyWiseScreen.Profile.route){
                CompleteScreen()
            }

            composable(MoneyWiseScreen.EditMov.route + "/{fecha}/{categoria}/{descripcion}/{monto}") { backStackEntry ->
                val fecha = backStackEntry.arguments?.getString("fecha")?.replace("-", "/") ?: ""
                val categoria = backStackEntry.arguments?.getString("categoria") ?: ""
                val descripcion = backStackEntry.arguments?.getString("descripcion") ?: ""
                val monto = backStackEntry.arguments?.getString("monto") ?: ""

                val movimiento = Movimiento(1,fecha, categoria, descripcion, monto)

                EditMovScreen(
                    movimiento = movimiento,
                    onSave = { updatedMovimiento ->
                        viewModel.updateMovimiento(movimiento, updatedMovimiento)
                        navController.navigate(MoneyWiseScreen.Movimientos.route) {
                            popUpTo(MoneyWiseScreen.Movimientos.route) { inclusive = true }
                        }
                    },
                    onCancel = {
                        navController.navigate(MoneyWiseScreen.Movimientos.route)
                    }
                )
            }

            composable(MoneyWiseScreen.DetallesMov.route + "/{fecha}/{categoria}/{descripcion}/{monto}") { backStackEntry ->
                val fecha = backStackEntry.arguments?.getString("fecha")?.replace("-", "/") ?: ""
                val categoria = backStackEntry.arguments?.getString("categoria") ?: ""
                val descripcion = backStackEntry.arguments?.getString("descripcion") ?: ""
                val monto = backStackEntry.arguments?.getString("monto") ?: ""

                val movimiento = Movimiento(fecha, categoria, descripcion, monto)

                DetallesMovScreen(
                    movimiento = movimiento,
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}
