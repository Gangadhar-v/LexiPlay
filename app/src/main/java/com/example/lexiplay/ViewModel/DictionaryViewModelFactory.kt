package com.example.lexiplay.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lexiplay.Repository.DictionaryRepository

class DictionaryViewModelFactory(private val repository:DictionaryRepository) :ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DictionaryViewModel::class.java)){
            return DictionaryViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown viewModel class")
    }
}