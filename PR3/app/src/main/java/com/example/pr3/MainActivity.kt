package com.example.pr3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.style.TextAlign

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            solarEnergyApp()
        }
    }
}

@Composable
fun solarEnergyApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFFAFAFA)
    ) {
        solarEnergyForm()
    }
}

@Composable
fun solarEnergyForm() {
    var dailyPower by remember { mutableStateOf(TextFieldValue("")) }
    var stdDeviation by remember { mutableStateOf(TextFieldValue("")) }
    var energyCost by remember { mutableStateOf(TextFieldValue("")) }
    var profit by remember { mutableStateOf(0.0) }

    val calculator = Calculator()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Розрахунок прибутку сонячної елекростанції",
            fontSize = 24.sp,
            color = Color.Black,
            textAlign = TextAlign.Center)

        Spacer(modifier = Modifier.height(24.dp))

        inputField(
            label = "Середньодобова потужність (МВт)",
            textState = dailyPower,
            onValueChange = { dailyPower = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        inputField(
            label = "Середнє квадратичне відхилення (МВт)",
            textState = stdDeviation,
            onValueChange = { stdDeviation = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        inputField(
            label = "Вартість електроенергії (₴/кВт⋅год)",
            textState = energyCost,
            onValueChange = { energyCost = it }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {
            val power = dailyPower.text.toDoubleOrNull() ?: 0.0
            val deviation = stdDeviation.text.toDoubleOrNull() ?: 0.0
            val cost = energyCost.text.toDoubleOrNull() ?: 0.0
            profit = calculator.calculateProfit(power, deviation, cost * 1000)
        }) {
            Text("Розрахувати")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(String.format("Очікуваний прибуток: ₴%.2f", profit), fontSize = 20.sp)
    }
}

@Composable
fun inputField(
    label: String,
    textState: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit
) {
    OutlinedTextField(
        value = textState,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        )
    )
}

@Preview(showBackground = true)
@Composable
fun defaultPreview() {
    solarEnergyApp()
}
