package edu.unicauca.moneywise.ui

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList

class MoneyWiseViewModel : ViewModel() {
    // Lista mutable de movimientos
    var movimientos: SnapshotStateList<Movimiento> = mutableStateListOf(
        Movimiento("19/08/2024", "Alimentación", "Supermercado", "$25000"),
        Movimiento("18/08/2024", "Transporte", "Gasolina", "$50000"),
        Movimiento("18/08/2024", "Entretenimiento", "Cine", "$12000"),
        Movimiento("14/08/2024", "Ahorro", "Mensual", "$200000")
    )

    // Función para actualizar un movimiento existente
    fun updateMovimiento(oldMovimiento: Movimiento, newMovimiento: Movimiento) {
        val index = movimientos.indexOf(oldMovimiento)
        if (index != -1) {
            movimientos[index] = newMovimiento
        }
    }
}

