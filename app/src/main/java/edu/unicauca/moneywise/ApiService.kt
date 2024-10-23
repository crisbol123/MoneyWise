package edu.unicauca.moneywise

import edu.unicauca.moneywise.ui.Movimiento
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("movimientos")
    suspend fun getMovimientos(@Header("Authorization") token: String): List<Movimiento>

    @PUT("movimientos")
    suspend fun updateMovimiento(
        @Header("Authorization") token: String,
        @Body movimiento: Movimiento
    ): Response<Void>

    @GET("usuarios")
    suspend fun getUsuario(@Header("Authorization") token: String): Usuario

    @POST("usuarios")
    suspend fun saveUsuario(@Body usuario: Usuario): Response<Void>
}
