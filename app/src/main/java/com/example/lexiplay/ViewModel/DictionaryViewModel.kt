package com.example.lexiplay.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lexiplay.Model.Data
import com.example.lexiplay.Repository.DictionaryRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class DictionaryViewModel(private val dictionaryRepository: DictionaryRepository): ViewModel() {

    private val _wordData = MutableLiveData<Data>()

    val wordData : LiveData<Data> get() = _wordData

    private val _error = MutableLiveData<String>()

    val error:LiveData<String> get()  = _error

    fun getWordDetails(word:String) {
        viewModelScope.launch {

            try {
                val response = dictionaryRepository.getWordDetails(word)
                if(response.isSuccessful && response.body() != null){
                    _wordData.postValue(response.body())
                }else{
                    _error.postValue("Error: ${response.message()}")
                }
            }catch(e: HttpException){
                _error.postValue("Network error ${e.message()}")
            }catch(e:IOException){
                _error.postValue("Please check your internet connection.")
            }catch(e:Exception){
                _error.postValue("An exception error is occurred")
            }
        }
    }
}