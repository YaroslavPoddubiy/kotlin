package com.example.pr4

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.pow
import kotlin.math.sqrt


fun calculate(power: Double): Double {
    return 10.5 / (sqrt(3.0) * (10.5.pow(2) / power + 10.5.pow(3) / 630))
}


@Composable
fun Task2(navController: NavController) {
    var power by remember { mutableStateOf(TextFieldValue("")) }
    var result by remember { mutableStateOf(0.0) }

    Box(modifier = Modifier.fillMaxSize()) {
        // Кнопка назад
        Button(
            onClick = { navController.navigate("home") },
            modifier = Modifier.align(Alignment.TopStart).padding(16.dp)
        ) {
            Text(text = "< Назад")
        }

        // Основний вміст
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .wrapContentHeight(Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Розрахунок параметрів")

            Spacer(modifier = Modifier.height(16.dp))

            // Поле введення для напруги
            inputField(
                label = "Потужність КЗ (МВ * А)",
                textState = power,
                onValueChange = { power = it },
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Кнопка для обчислення
            Button(
                onClick = {
                    result = calculate(power.text.toDoubleOrNull() ?: 0.0)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Обрахувати")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(String.format("Струм КЗ на шинах 10 кВ ГПП: %.2fкА", result), fontSize = 20.sp)
        }
    }
}