package com.example.pr4

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.ceil
import kotlin.math.sqrt


fun calculate(voltage: Double, shortCircuitCurrent: Double, faultDuration: Double, load: Double): Double {
    val im = (load / 2.0) / (sqrt(3.0) * voltage)
    val impa = 2 * im
    val sek = im / 1.4
    var smin = (shortCircuitCurrent * 1000 * sqrt(faultDuration)) / 92.0
    if (smin <= sek) {
        smin = sek
    }
    return ceil(smin / 10) * 10
}


@Composable
fun Task1(navController: NavController) {
    var voltage by remember { mutableStateOf(TextFieldValue("")) }
    var shortCircuitCurrent by remember { mutableStateOf(TextFieldValue("")) }
    var faultDuration by remember { mutableStateOf(TextFieldValue("")) }
    var load by remember { mutableStateOf(TextFieldValue("")) }
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
                label = "Напруга (кВ)",
                textState = voltage,
                onValueChange = { voltage = it },
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Поле введення для струму короткого замикання
            inputField(
                label = "Струм КЗ (кА)",
                textState = shortCircuitCurrent,
                onValueChange = { shortCircuitCurrent = it },
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Поле введення для часу увімкнення КЗ
            inputField(
                label = "Час вмикання КЗ (с)",
                textState = faultDuration,
                onValueChange = { faultDuration = it },
            )

            Spacer(modifier = Modifier.height(8.dp))

            inputField(
                label = "Очікуване навантаження (кВ * А)",
                textState = load,
                onValueChange = { load = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Кнопка для обчислення
            Button(
                onClick = {
                    val _voltage = voltage.text.toDoubleOrNull() ?: 0.0
                    val _shortCircuitCurrent = shortCircuitCurrent.text.toDoubleOrNull() ?: 0.0
                    val _faultDuration = faultDuration.text.toDoubleOrNull() ?: 0.0
                    val _load = load.text.toDoubleOrNull() ?: 0.0

                    result = calculate(_voltage, _shortCircuitCurrent, _faultDuration, _load)
                          },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Обрахувати")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(String.format("Рекомендований переріз кабеля: %.2fмм^2", result), fontSize = 20.sp)
        }
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