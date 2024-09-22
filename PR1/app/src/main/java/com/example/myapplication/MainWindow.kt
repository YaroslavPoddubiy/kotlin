package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainWindow : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_window)

        // Отримуємо посилання на поля вводу
        val task1_button: Button = findViewById<Button>(R.id.task1)
        val task2_button: Button = findViewById<Button>(R.id.task2)

        task1_button.setOnClickListener {
            val intent = Intent(this, Task1::class.java)
            startActivity(intent)
        }

        task2_button.setOnClickListener {
            val intent = Intent(this, Task2::class.java)
            startActivity(intent)
        }
    }

}