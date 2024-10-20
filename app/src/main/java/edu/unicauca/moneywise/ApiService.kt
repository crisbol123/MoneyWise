package edu.unicauca.moneywise

import edu.unicauca.moneywise.ui.Movimiento
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @GET("movimientos")
    suspend fun getMovimientos(): List<Movimiento>

    @PUT("movimientos/{id}")
    suspend fun updateMovimiento(@Path("id") id: Long, @Body movimiento: Movimiento): Response<Void>
}
