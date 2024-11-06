package com.example.lexiplay

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val retrofitService:RetrofitService
    init{

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.dictionaryapi.dev/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        retrofitService =retrofit.create(RetrofitService::class.java)
    }

}