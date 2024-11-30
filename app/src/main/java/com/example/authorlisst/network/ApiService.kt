package com.example.authorlist.network

import com.example.authorlist.model.ApiData
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("character")
    fun getCharacters(): Call<ApiData>
}
