package com.example.pr4

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.pow
import kotlin.math.sqrt


fun calculateCurrency(rResistance: Double, xResistance: Double, phases: Int) : Double {
    val rc = rResistance * 0.009

    val x = (xResistance + 233) * 0.009
    val z = sqrt(rc.pow(2) + x.pow(2))
    val i = 11000 / z
    if (phases == 3){
        return i / sqrt(3.0)
    }
    if (phases == 2){
        return i / 2
    }
    return 0.0
}


fun getResultText(mode: String): String {
    var result = String()

    if (mode == "Аварійний режим") {
        result = "Підстанція не має аварійного режиму"
    }
    else if (mode == "Нормальний режим") {
        result += "Струм трифазного КЗ в нормальному режимі: "
        result += String.format("%.2fА\n", calculateCurrency(10.65, 24.02, 3))
        result += "Струм двофазного КЗ в нормальному режимі: "
        result += String.format("%.2fА", calculateCurrency(10.65, 24.02, 2))
    }
    else if (mode == "Мінімальний режим") {
        result += "Струм трифазного КЗ в мінімальному режимі: "
        result += String.format("%.2fА\n", calculateCurrency(34.88, 65.68, 3))
        result += "Струм двофазного КЗ мінімальному режимі: "
        result += String.format("%.2fА", calculateCurrency(34.88, 65.68, 2))
    }
    return result
}


@Composable
fun Task3(navController: NavController) {

    var mode by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

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
            ComboBox(
                items = listOf("Нормальний режим", "Мінімальний режим", "Аварійний режим"),
                selectedItem = mode,
                onItemSelected = { mode = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Кнопка для обчислення
            Button(
                onClick = {
                    result = getResultText(mode.toString())
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Обрахувати")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(result, fontSize = 20.sp)
        }
    }
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
        onExpandedChange = { expanded = !expanded } // Toggle expanded state
    ) {
        // TextField inside ExposedDropdownMenuBox
        TextField(
            value = selectedItem,
            onValueChange = {}, // Read-only field, so no changes
            readOnly = true, // Make it read-only
            modifier = Modifier
                .menuAnchor() // Required to attach dropdown to this TextField
                .fillMaxWidth(),
            label = { Text("Виберіть режим") }
        )

        // Dropdown menu items
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false } // Close dropdown when clicking outside
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = { Text(item) },
                    onClick = {
                        onItemSelected(item) // Update selected item
                        expanded = false // Close the dropdown
                    }
                )
            }
        }
    }
}