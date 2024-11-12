package com.shreyash.github.starwar.retrofit

 import com.shreyash.github.starwar.data.Matches
 import com.shreyash.github.starwar.data.Users
 import retrofit2.Call
 import retrofit2.http.GET


interface ApiService {
    @GET("b/IKQQ")
    fun getUsers(): Call<List<Users>>

    @GET("b/JNYL")
    fun getMatches(): Call<List<Matches>>
}