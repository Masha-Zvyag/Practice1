package com.example.data_thread

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.data_thread.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val runn1 = Runnable {
            binding.tvInfo.append("runn1\n")
        }

        val runn2 = Runnable {
            binding.tvInfo.append("runn2\n")
        }

        val runn3 = Runnable {
            binding.tvInfo.append("runn3\n")
        }

        val t = Thread(
            Runnable {
                try {
                    TimeUnit.SECONDS.sleep(2)
                    runOnUiThread(runn1)

                    TimeUnit.SECONDS.sleep(1)
                    binding.tvInfo.postDelayed(runn3, 2000)
                    binding.tvInfo.post(runn2)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        )
        t.start()
    }
}