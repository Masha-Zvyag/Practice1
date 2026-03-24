package com.example.thread

import android.os.Bundle
import android.os.Process
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.thread.databinding.ActivityMainBinding
import java.util.Arrays

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mainThread = Thread.currentThread()
        binding.textViewInfo.text = "имя текущего потока: ${mainThread.name}"

        mainThread.name = "моя группа: бсбо-52-24, номер по списку: 13, мой любимый фильм: гарри поттер"
        binding.textViewInfo.append("\nновое имя потока: ${mainThread.name}")

        Log.d(MainActivity::class.java.simpleName, "stack: ${Arrays.toString(mainThread.stackTrace)}")
        Log.d(MainActivity::class.java.simpleName, "group: ${mainThread.threadGroup}")

        binding.buttonMirea.setOnClickListener {
            val lessonsText = binding.editTextLessons.text.toString()
            val daysText = binding.editTextDays.text.toString()

            if (lessonsText.isEmpty() || daysText.isEmpty()) {
                binding.textViewResult.text = "заполните оба поля"
                return@setOnClickListener
            }

            val lessons = lessonsText.toInt()
            val days = daysText.toInt()

            if (days == 0) {
                binding.textViewResult.text = "количество учебных дней не может быть равно 0"
                return@setOnClickListener
            }

            Thread(Runnable {
                val numberThread = counter++

                Log.d(
                    "ThreadProject",
                    String.format(
                        "запущен поток № %d студентом группы № %s номер по списку № %d",
                        numberThread,
                        "бсбо-52-24",
                        13
                    )
                )

                Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND)

                val endTime = System.currentTimeMillis() + 20 * 1000

                while (System.currentTimeMillis() < endTime) {
                    try {
                        Thread.sleep(300)
                        Log.d(MainActivity::class.java.simpleName, "endtime: $endTime")
                    } catch (e: Exception) {
                        throw RuntimeException(e)
                    }
                }

                val average = lessons.toDouble() / days.toDouble()

                runOnUiThread {
                    binding.textViewResult.text =
                        "среднее количество пар в день: %.2f".format(average)
                }

                Log.d("ThreadProject", "выполнен поток № $numberThread")
            }).start()
        }
    }
}