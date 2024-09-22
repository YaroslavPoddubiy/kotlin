package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class Task2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.task2)

        // Отримуємо посилання на поля вводу
        val carbon = findViewById<EditText>(R.id.carbon)
        val hydrogen = findViewById<EditText>(R.id.hydrogen)
        val sulfur = findViewById<EditText>(R.id.sulfur)
        val vanadium = findViewById<EditText>(R.id.vanadium)
        val oxygen = findViewById<EditText>(R.id.oxygen)
        val moisture = findViewById<EditText>(R.id.moisture)
        val ash = findViewById<EditText>(R.id.ash)
        val wormth = findViewById<EditText>(R.id.wormth)

        // Отримуємо посилання на кнопку і текстове поле для результату
        val calculateButton = findViewById<Button>(R.id.calculate_button)
        val backButton = findViewById<Button>(R.id.back_button)

        val workComponentsTextView = findViewById<TextView>(R.id.workComponentsTextView)
        val workWormthTextView = findViewById<TextView>(R.id.workWormthTextView)

        // Додаємо обробник натискання на кнопку
        calculateButton.setOnClickListener {
            // Отримуємо значення з полів і перетворюємо на числа
            val value1 = carbon.text.toString().toDoubleOrNull() ?: 0.0
            val value2 = hydrogen.text.toString().toDoubleOrNull() ?: 0.0
            val value3 = sulfur.text.toString().toDoubleOrNull() ?: 0.0
            val value4 = vanadium.text.toString().toDoubleOrNull() ?: 0.0
            val value5 = oxygen.text.toString().toDoubleOrNull() ?: 0.0
            val value6 = moisture.text.toString().toDoubleOrNull() ?: 0.0
            val value7 = ash.text.toString().toDoubleOrNull() ?: 0.0
            val value8 = wormth.text.toString().toDoubleOrNull() ?: 0.0

            val components = arrayOf(value1, value2, value3, value4, value5, value6, value7)

            // Виконуємо обчислення (наприклад, суму всіх значень)
            val work_components = get_work_components(components)

            // Виводимо результат
            workComponentsTextView.text = get_text("Cклад робочого палива", work_components)
            workWormthTextView.text = "Нижча теплота згоряння робочого палива: " +
                    String.format("%.3f", get_work_wormth(value8, work_components[5], work_components[6])) + "Мдж/кг"
        }

        backButton.setOnClickListener {
            val intent = Intent(this, MainWindow::class.java)
            startActivity(intent)
        }
    }

    private fun get_text(title: String, components: Array<Double>): String {
        val components_names = arrayOf("Вуглець", "Водень", "Сірка", "Ванадій", "Кисень", "Волога", "Зола")
        var text = "$title:\n"
        for (i in components.indices) {
            text += components_names[i] + ": " + String.format("%.2f", components[i])
            if (i == 3){
                text += " мг/кг\n"
            }
            else{
                text += "%\n"
            }
        }
        return text
    }

    private fun get_afr_coef(w: Double, a: Double): Double {
        return (100 - w - a) / 100
    }

    private fun get_work_components(components: Array<Double>): Array<Double> {
        val coef = get_afr_coef(components[6], components[5])
        var work_components = components.copyOf()
        for (i in work_components.indices) {
            work_components[i] = coef * work_components[i]
        }
        work_components[5] = components[5]
        work_components[6] = ((100 - components[5]) / 100) * components[6]
        return work_components
    }

    private fun get_work_wormth(wormth: Double, w: Double, a: Double): Double {
        return wormth * (100 - w - a) / 100 - 0.025 * w
    }
}