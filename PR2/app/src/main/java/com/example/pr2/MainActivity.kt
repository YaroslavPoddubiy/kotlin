package com.example.pr2

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var fuelWormth = arrayOf(20.47, 40.4, 33.08);
    private var fuelVolume: Double = 0.0;
    private val fuelAsh = arrayOf(25.2, 0.15, 0.0)
    private val fuelAshCoef = arrayOf(0.8, 1.0, 0.0)
    private val combustibleSubstance = arrayOf(1.5, 0.0, 0.0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val fuelType = findViewById<Spinner>(R.id.spinner)
        setFuelTypes(fuelType)
        val fuelVolumeEdit = findViewById<EditText>(R.id.fuelVolume)

        val calculateButton = findViewById<Button>(R.id.calculateButton)

        val resultTextView = findViewById<TextView>(R.id.resultTextView)

        calculateButton.setOnClickListener {
            val itemId = fuelType.selectedItemPosition
            this.fuelVolume = fuelVolumeEdit.text.toString().toDoubleOrNull() ?: 0.0;
            resultTextView.text = String.format("Валовий викид дорівнює %.3f", getEmission(itemId))
        }

    }

    private fun setFuelTypes(spinner: Spinner){
        val fuelTypes = ArrayList<String>()
        fuelTypes.add("Донецьке газове вугілля марки ГР")
        fuelTypes.add("Високосірчистий мазут марки 40")
        fuelTypes.add("Природний газ із газопроводу Уренгой- Ужгород")
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, fuelTypes)
        spinner.adapter = adapter
    }

    private fun getEmissionIndicator(itemId: Int): Double{
        return (1000000 / fuelWormth[itemId]) * fuelAshCoef[itemId] *
                (fuelAsh[itemId] / (100 - combustibleSubstance[itemId])) * 0.015
    }

    private fun getEmission(itemId: Int): Double{
        val emission = getEmissionIndicator(itemId)
        return 0.000001 * emission * this.fuelWormth[itemId] * this.fuelVolume
    }
}