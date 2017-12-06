package com.example.android.tvappiot

import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthApi {
    @Headers("Accept: application/json")
    @POST("oauth/token")
    fun authenticate(@Body body: PasswordGrantBody): Call<Token>

    companion object {
        fun create(): AuthApi {
            val gson = GsonBuilder().create()
            val retro = Retrofit.Builder()
                    .baseUrl(Application.AUTH_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
            return retro.create(AuthApi::class.java)
        }
    }
}