package com.example.lexiplay.Repository

import com.example.lexiplay.Model.Data
import com.example.lexiplay.RetrofitInstance
import retrofit2.Response

class DictionaryRepository() {

    suspend fun getWordDetails(word:String):Response<Data>{
        return RetrofitInstance.retrofitService.getWordDetails(word)
    }
}