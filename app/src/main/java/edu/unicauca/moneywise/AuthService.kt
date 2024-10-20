package edu.unicauca.moneywise

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("login")
    fun login(@Body request: LoginRequest): Call<AuthTokenResponse>
}
