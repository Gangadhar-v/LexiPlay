package com.example.lexiplay

import com.example.lexiplay.Model.Data
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {

    @GET("api/v2/entries/en/{word}")
    suspend fun getWordDetails(@Path("word") word:String): Response<Data>
}