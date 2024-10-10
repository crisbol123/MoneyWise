package edu.unicauca.moneywise

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.lifecycle.viewmodel.compose.viewModel

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
import edu.unicauca.moneywise.ui.LoginScreen
import edu.unicauca.moneywise.ui.MoneyWiseViewModel
import edu.unicauca.moneywise.ui.Movimiento
import edu.unicauca.moneywise.ui.MovimientosScreen


enum class MoneyWiseScreen(val route: String) {
    Login("login"),
    Home("home"),
    Movimientos("movimientos"),
    Profile("profile")
}


@Composable
fun MoneyWiseApp(
    viewModel: MoneyWiseViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        bottomBar = {    if (currentRoute != MoneyWiseScreen.Login.route) {
            MoneyWiseBottomNavigation(navController)
        } }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MoneyWiseScreen.Login.route,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(MoneyWiseScreen.Login.route) {
                LoginScreen( onNextButtonClicked = {
                    navController.navigate(MoneyWiseScreen.Home.route)
                })
            }

            composable(MoneyWiseScreen.Home.route) {
                Text("Home")
            }
            composable(MoneyWiseScreen.Movimientos.route) {
                val movimientos = listOf(
                    Movimiento("19/08/2024", "Alimentación", "Supermercado", "$25000"),
                    Movimiento("18/08/2024", "Transporte", "Gasolina", "$50000"),
                    Movimiento("18/08/2024", "Entretenimiento", "Cine", "$12000"),
                    Movimiento("14/08/2024", "Ahorro", "Mensual", "$200000")
                )

                MovimientosScreen(
                    movimientos = movimientos,
                    onEditarClicked = {  },
                    onAgregarClicked = {  },
                    onDetallesClicked = {}
                )

            }
            composable(MoneyWiseScreen.Profile.route) {

            }
        }
    }
}


@Composable
fun MoneyWiseBottomNavigation(navController: NavHostController) {
    val items = listOf(
        MoneyWiseScreen.Home,
        MoneyWiseScreen.Movimientos,
        MoneyWiseScreen.Profile
    )


    NavigationBar() {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { screen ->
            NavigationBarItem(
                icon = {
                    when (screen) {
                        MoneyWiseScreen.Home -> Icon(Icons.Filled.Home, contentDescription = null)
                        MoneyWiseScreen.Movimientos -> Icon(Icons.Filled.List, contentDescription = null)
                        MoneyWiseScreen.Profile -> Icon(Icons.Filled.Person, contentDescription = null)
                        MoneyWiseScreen.Login -> TODO()
                    }
                },
                label = { Text(screen.name) },
                selected = currentRoute == screen.route,
                onClick = {
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route)
                    }
                }
            )
        }
    }
}
