package com.example.android.tvappiot

import android.content.Context
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface WebAppApi {

    @GET("devices")
    fun loadDevices(): Call<ArrayList<Device>>

    @GET("devices/{id}/up")
    fun getUplinkMessage(@Path("id") id: Int): Call<Uplink>

    @POST("devices/{id}/down")
    fun sendDownlinkMessage(@Path("id") id: Int, @Body body: Downlink?): Call<String>

    companion object {
        fun create(context: Context): WebAppApi {

            val sharedPref = context.getSharedPreferences(Application.PREF_NAME, 0)
            val token  = sharedPref.getString(Application.PREF_TOKEN_NAME, "")

            val gson = GsonBuilder().create()
            val okHttp = OkHttpClient().newBuilder().addInterceptor { chain ->
                val request = chain.request()
                val builder = request.newBuilder()
                        .addHeader("Accept", "application/json")
                        .addHeader("Authorization", "Bearer " + token)
                chain.proceed(builder.build())
            }.build()

            val retro = Retrofit.Builder()
                    .baseUrl(Application.BASE_URL)
                    .client(okHttp)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()

            return retro.create(WebAppApi::class.java)
        }
    }
}

