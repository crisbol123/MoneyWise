import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import edu.unicauca.moneywise.ApiService
import edu.unicauca.moneywise.ui.Movimiento
import retrofit2.Response

class MoneyWiseViewModel : ViewModel() {

    // Retrofit setup
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.1.40:8090/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService: ApiService = retrofit.create(ApiService::class.java)

    var movimientos: SnapshotStateList<Movimiento> = mutableStateListOf()

    init {
        // Llamar a la función para cargar los movimientos al iniciar el ViewModel
        fetchMovimientos()
    }

    // Función para cargar los movimientos desde la API
    private fun fetchMovimientos() {
        viewModelScope.launch {
            try {
                val response = apiService.getMovimientos()
                movimientos.clear()
                movimientos.addAll(response) // Actualiza la lista con los datos de la API
            } catch (e: Exception) {
                // Manejar errores aquí (por ejemplo, mostrar un mensaje de error)
                e.printStackTrace()
            }
        }
    }

    // Función para actualizar un movimiento existente en el servidor
    fun updateMovimiento(oldMovimiento: Movimiento, newMovimiento: Movimiento) {
        viewModelScope.launch {
            try {
                val response: Response<Void> = apiService.updateMovimiento(oldMovimiento.id, newMovimiento)
                if (response.isSuccessful) {
                    // Actualiza localmente la lista de movimientos
                    val index = movimientos.indexOf(oldMovimiento)
                    if (index != -1) {
                        movimientos[index] = newMovimiento
                    }
                } else {
                    // Manejar el caso de error, por ejemplo, un error del servidor
                    println("Error actualizando movimiento: ${response.errorBody()}")
                }
            } catch (e: Exception) {
                // Manejar errores de la solicitud
                e.printStackTrace()
            }
        }
    }
}
