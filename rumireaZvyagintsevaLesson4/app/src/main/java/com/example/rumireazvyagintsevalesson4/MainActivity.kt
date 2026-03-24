package com.example.rumireazvyagintsevalesson4

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.rumireazvyagintsevalesson4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var isPlaying = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textViewSong.text = "Blinding Lights"
        binding.textViewArtist.text = "The Weeknd"
        binding.textViewStatus.text = "Статус: остановлено"
        binding.buttonPlay.text = "Play"

        binding.buttonPlay.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                isPlaying = !isPlaying

                if (isPlaying) {
                    binding.buttonPlay.text = "Pause"
                    binding.textViewStatus.text = "Статус: воспроизведение"
                } else {
                    binding.buttonPlay.text = "Play"
                    binding.textViewStatus.text = "Статус: пауза"
                }
            }
        })

        binding.buttonNext.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                binding.textViewSong.text = "Save Your Tears"
                binding.textViewArtist.text = "The Weeknd"
                binding.textViewStatus.text = "Статус: следующий трек"
                isPlaying = false
                binding.buttonPlay.text = "Play"
            }
        })

        binding.buttonPrev.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                binding.textViewSong.text = "Starboy"
                binding.textViewArtist.text = "The Weeknd"
                binding.textViewStatus.text = "Статус: предыдущий трек"
                isPlaying = false
                binding.buttonPlay.text = "Play"
            }
        })
    }
}