package com.example.retrofitexample.network

import com.example.retrofitexample.model.HerosItem
import retrofit2.Call
import retrofit2.http.GET

interface superheroApi {
    @GET("marvel") // making get request at marvel end-point
    fun getHeroes(): Call<List<HerosItem?>?>?
}