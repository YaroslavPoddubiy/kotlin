package com.example.pr5

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


fun calculate(lineReliability: Double, lineRepairTime: Double, lineLength: Double): Double {
    val lineComponentsReliability = listOf(0.01, 0.015, 0.02, 0.03)
    val lineComponentsQuantity = listOf(1, 1, 1, 6)
    val lineComponentsRepairTime = listOf(30, 100, 15, 2)

    var refuseFrequency = lineReliability * lineLength
    var averageRepairTime = refuseFrequency * lineRepairTime

    for (i in lineComponentsReliability.indices) {
        refuseFrequency += lineComponentsReliability[i] * lineComponentsQuantity[i]
        averageRepairTime += lineComponentsReliability[i] * lineComponentsQuantity[i] * lineComponentsRepairTime[i]
    }

    averageRepairTime /= refuseFrequency

    val emergencyDownTimeCoefficient = refuseFrequency * averageRepairTime / 8760
    val planDownTimeCoefficient = 1.2 * 43 / 8760

    val doubleCircleRefuseFrequency = 2 * refuseFrequency * (emergencyDownTimeCoefficient + planDownTimeCoefficient) + 0.02

    return refuseFrequency / doubleCircleRefuseFrequency
}


@Composable
fun Task1(navController: NavController) {
    val linesNames = listOf("ПЛ-110", "ПЛ-35", "ПЛ-10", "КЛ-10 (траншея)", "КЛ-10 (кабельний канал)")
    val linesReliability = listOf(0.007, 0.02, 0.02, 0.03, 0.005)
    val linesRepairTime = listOf(10.0, 8.0, 10.0, 44.0, 17.5)

    var lineName by remember { mutableStateOf(linesNames[0]) }
    var lineLength by remember { mutableStateOf(TextFieldValue("")) }
    var result by remember { mutableStateOf(0.0) }

    Box(modifier = Modifier.fillMaxSize()) {
        Button(
            onClick = { navController.navigate("home") },
            modifier = Modifier.align(Alignment.TopStart).padding(16.dp)
        ) {
            Text(text = "< Назад")
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .wrapContentHeight(Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Розрахунок параметрів")

            Spacer(modifier = Modifier.height(16.dp))

            ComboBox(
                items = linesNames,
                selectedItem = lineName,
                onItemSelected = { lineName = it }
            )

            inputField(
                label = "Довжина лінії (км)",
                textState = lineLength,
                onValueChange = { lineLength = it },
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val lineIndex = linesNames.indexOf(lineName)
                    val lineReliability = linesReliability[lineIndex]
                    val lineRepairTime = linesRepairTime[lineIndex]
                    val len = lineLength.text.toDoubleOrNull() ?: 0.0

                    result = calculate(lineReliability, lineRepairTime, len)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Обрахувати")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(String.format("Двоколова система надійніша за одноколову в %.2f разів", result), fontSize = 20.sp)
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComboBox(
    items: List<String>,
    selectedItem: String,
    onItemSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {

        TextField(
            value = selectedItem,
            onValueChange = {},
            readOnly = true,
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            label = { Text("Виберіть режим") }
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = { Text(item) },
                    onClick = {
                        onItemSelected(item)
                        expanded = false
                    }
                )
            }
        }
    }
}