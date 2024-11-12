package com.shreyash.github.starwar.retrofit

// RetrofitClient.kt
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.net.ssl.HostnameVerifier

object RetrofitClient {
    private const val BASE_URL = "https://jsonkeeper.com/"  // Keep base URL as is if you choose this option

    private val okHttpClient = OkHttpClient.Builder()
        .hostnameVerifier(HostnameVerifier { hostname, session ->
            hostname == "jsonkeeper.com" || hostname == "www.jsonkeeper.com"
        })
        .build()

    val instance: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}