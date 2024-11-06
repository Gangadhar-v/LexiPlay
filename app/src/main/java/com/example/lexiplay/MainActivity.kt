package com.example.lexiplay

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.example.lexiplay.Repository.DictionaryRepository
import com.example.lexiplay.ViewModel.DictionaryViewModel
import com.example.lexiplay.ViewModel.DictionaryViewModelFactory
import com.example.lexiplay.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewModel:DictionaryViewModel by viewModels{
        DictionaryViewModelFactory(DictionaryRepository())
    }
    private lateinit var binding:ActivityMainBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)






        binding.searchButton.setOnClickListener {
            val word = binding.wordInput.text.toString()
            if (word.isNotEmpty()) {
                viewModel.getWordDetails(word)
            } else {
                Toast.makeText(this, "Please enter a word", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.wordData.observe(this, Observer { data ->
            if (data.isNotEmpty()) {
                val wordDetails = data[0]
                binding.wordTextView.text = wordDetails.word
                binding.definitionTextView.text = wordDetails.meanings[0].definitions[0].definition

                // Play audio if available
                wordDetails.phonetics.firstOrNull()?.audio?.let { audioUrl ->
                    val mediaPlayer = MediaPlayer()
                    mediaPlayer.setDataSource(audioUrl)
                    mediaPlayer.prepare()
                    binding.playAudioButton.setOnClickListener {
                        mediaPlayer.start()
                    }
                }
            }
        })

        viewModel.error.observe(this, Observer { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
        })


    }
}