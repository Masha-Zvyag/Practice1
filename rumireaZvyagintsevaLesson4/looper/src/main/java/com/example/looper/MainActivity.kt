package com.example.looper

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.looper.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var myLooper: MyLooper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mainThreadHandler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                Log.d(
                    MainActivity::class.java.simpleName,
                    "task execute. this is result: ${msg.data.getString("result")}"
                )
            }
        }

        myLooper = MyLooper(mainThreadHandler)
        myLooper.start()

        binding.buttonMirea.setOnClickListener {
            val ageText = binding.editTextAge.text.toString()
            val jobText = binding.editTextJob.text.toString()

            if (ageText.isEmpty() || jobText.isEmpty()) {
                Toast.makeText(this@MainActivity, "заполните оба поля", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val age = ageText.toInt()

            if (myLooper.mHandler == null) {
                Toast.makeText(this@MainActivity, "поток еще не готов", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val msg = Message.obtain()
            val bundle = Bundle()
            bundle.putInt("AGE", age)
            bundle.putString("JOB", jobText)
            msg.data = bundle

            myLooper.mHandler.sendMessage(msg)
        }
    }
}