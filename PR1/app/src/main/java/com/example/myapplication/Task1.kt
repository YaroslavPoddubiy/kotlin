package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class Task1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.task1)

        // Отримуємо посилання на поля вводу
        val carbon = findViewById<EditText>(R.id.carbon)
        val hydrogen = findViewById<EditText>(R.id.hydrogen)
        val sulfur = findViewById<EditText>(R.id.sulfur)
        val nitrogen = findViewById<EditText>(R.id.vanadium)
        val oxygen = findViewById<EditText>(R.id.oxygen)
        val moisture = findViewById<EditText>(R.id.moisture)
        val ash = findViewById<EditText>(R.id.ash)

        // Отримуємо посилання на кнопку і текстове поле для результату
        val calculateButton = findViewById<Button>(R.id.calculate_button)
        val backButton = findViewById<Button>(R.id.back_button)

        val dryComponentsTextView = findViewById<TextView>(R.id.dryComponentstextView)
        val ashFreeComponentsTextView = findViewById<TextView>(R.id.ashFreeComponentsTextView)

        val workWormthTextView = findViewById<TextView>(R.id.workWormthTextView)
        val dryWormthTextView = findViewById<TextView>(R.id.dryWormthTextView)
        val ashFreeWormthTextView = findViewById<TextView>(R.id.ashFreeWormthTextView)

        // Додаємо обробник натискання на кнопку
        calculateButton.setOnClickListener {
            // Отримуємо значення з полів і перетворюємо на числа
            val value1 = carbon.text.toString().toDoubleOrNull() ?: 0.0
            val value2 = hydrogen.text.toString().toDoubleOrNull() ?: 0.0
            val value3 = sulfur.text.toString().toDoubleOrNull() ?: 0.0
            val value4 = nitrogen.text.toString().toDoubleOrNull() ?: 0.0
            val value5 = oxygen.text.toString().toDoubleOrNull() ?: 0.0
            val value6 = moisture.text.toString().toDoubleOrNull() ?: 0.0
            val value7 = ash.text.toString().toDoubleOrNull() ?: 0.0

            val components = arrayOf(value1, value2, value3, value4, value5, value6, value7)

            // Виконуємо обчислення (наприклад, суму всіх значень)
            val dry_components = get_dry_components(components)
            val ash_free_components = get_ash_free_components(components)

            // Виводимо результат
            dryComponentsTextView.text = get_text("Cклад сухого палива", dry_components)
            ashFreeComponentsTextView.text = get_text("Склад горючого палива", ash_free_components)

            val wormth = get_wormth(components) / 1000.0

            workWormthTextView.text = "Нижча теплота згоряння робочого складу палива: " +
                    String.format("%.2f", wormth) + "МДж/кг"
            dryWormthTextView.text = "Нижча теплота згоряння сухого складу палива: " +
                    String.format("%.2f", get_dry_wormth(wormth, components[5], components[6])) + "МДж/кг"
            ashFreeWormthTextView.text = "Нижча теплота згоряння горючого складу палива: " +
                    String.format("%.2f", get_af_wormth(wormth, components[5], components[6])) + "МДж/кг"
        }

        backButton.setOnClickListener {
            val intent = Intent(this, MainWindow::class.java)
            startActivity(intent)
        }
    }

    private fun get_text(title: String, components: Array<Double>): String {
        val components_names = arrayOf("Вуглець", "Водень", "Сірка", "Азот", "Кисень", "Волога", "Зола")
        var text = "$title:\n"
        for (i in components.indices) {
            text += components_names[i] + ": " + String.format("%.3f", components[i]) + "%\n"
        }
        text += "Сума: "
        if (check(components)){
            text += "100%"
        }
        else{
            text += components.sum().toString() + "%"
        }
        return text
    }

    private fun get_rd_coef(w: Double): Double {
        return 100 / (100 - w)
    }

    private fun get_raf_coef(w: Double, a: Double): Double {
        return 100 / (100 - w - a)
    }

    private fun get_dry_components(work_components: Array<Double>): Array<Double> {
        val coef = get_rd_coef(work_components[5])
        var dry_components = work_components.copyOf()
        dry_components[5] = 0.0
        for (i in dry_components.indices) {
            dry_components[i] = coef * dry_components[i]
        }
        return dry_components
    }

    private fun get_ash_free_components(work_components: Array<Double>): Array<Double> {
        val coef = get_raf_coef(work_components[5], work_components[6])
        var ach_free_components = work_components.copyOf()
        ach_free_components[5] = 0.0
        ach_free_components[6] = 0.0
        for (i in ach_free_components.indices) {
            ach_free_components[i] = coef * ach_free_components[i]
        }
        return ach_free_components
    }

    private fun get_wormth(components: Array<Double>): Double {
        return 339 * components[0] + 1030 * components[1] - 108.8 * (components[4] - components[2]) -
                25 * components[5]
    }

    private fun get_dry_wormth(wormth: Double, w: Double, a: Double): Double {
        return (wormth + 0.025 * w) * (100 / (100 - w))
    }

    private fun get_af_wormth(wormth: Double, w: Double, a: Double): Double {
        return (wormth + 0.025 * w) * (100 / (100 - w - a))
    }

    private fun check(components: Array<Double>): Boolean {
        val allowable_error = 0.0000001
        return (100.0 - allowable_error < components.sum()) and (components.sum() < 100.0 + allowable_error)
    }
}