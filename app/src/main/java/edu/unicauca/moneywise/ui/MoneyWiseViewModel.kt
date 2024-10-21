import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import edu.unicauca.moneywise.ApiService
import edu.unicauca.moneywise.Usuario
import edu.unicauca.moneywise.ui.Movimiento
import retrofit2.Response

class MoneyWiseViewModel : ViewModel() {

    private lateinit var authToken: String // Token de autenticación

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.1.13:8090/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService: ApiService = retrofit.create(ApiService::class.java)

    var movimientos: SnapshotStateList<Movimiento> = mutableStateListOf()
    var usuario: Usuario? = null

    fun setAuthToken(token: String) {
        authToken = token
        fetchMovimientos()
        fetchUsuario()
    }


    private fun fetchMovimientos() {
        if (::authToken.isInitialized) {
            viewModelScope.launch {
                try {
                    val response = apiService.getMovimientos("Bearer $authToken")
                    movimientos.clear()
                    movimientos.addAll(response)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    fun updateMovimiento(newMovimiento: Movimiento) {
        if (::authToken.isInitialized) {
            viewModelScope.launch {
                try {
                    val response: Response<Void> = apiService.updateMovimiento(
                        "Bearer $authToken",
                        newMovimiento
                    )
                    if (response.isSuccessful) {
                        println("Movimiento actualizado")
                        fetchMovimientos()
                    } else {
                        println("Error actualizando movimiento: ${response.errorBody()}")
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun fetchUsuario() {
        if (::authToken.isInitialized) {
            viewModelScope.launch {
                try {
                    val response =
                        apiService.getUsuario("Bearer $authToken")
                    usuario = response
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

    }

    fun saveUsuario(usuario: Usuario) {
        viewModelScope.launch {
            try {
                val response: Response<Void> = apiService.saveUsuario(
                    usuario
                )
                if (response.isSuccessful) {
                    println("usuario agregado")
                    fetchMovimientos()
                } else {
                    println("Error agregando usuario: ${response.errorBody()}")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
