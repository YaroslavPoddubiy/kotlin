package com.example.pr5

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


fun calculate(refuseFrequency: Double, repairTime: Double, planDownTime: Double, emergencyLoss: Double, planLoss: Double) : Double {
    val mEmergency = refuseFrequency * repairTime * 5120 * 6451
    val mPlan = planDownTime * 5120 * 6451
    val m = mEmergency * emergencyLoss + mPlan * planLoss
    return m
}


@Composable
fun Task2(navController: NavController) {
    var refuseFrequency by remember { mutableStateOf(TextFieldValue("")) }
    var repairTime by remember { mutableStateOf(TextFieldValue("")) }
    var planDownTime by remember { mutableStateOf(TextFieldValue("")) }
    var emergencyLoss by remember { mutableStateOf(TextFieldValue("")) }
    var planLoss by remember { mutableStateOf(TextFieldValue("")) }
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

            inputField(
                label = "Частота відмов (/рік)",
                textState = refuseFrequency,
                onValueChange = { refuseFrequency = it },
            )

            Spacer(modifier = Modifier.height(8.dp))

            inputField(
                label = "Тривалість відновлення (років)",
                textState = repairTime,
                onValueChange = { repairTime = it },
            )

            Spacer(modifier = Modifier.height(8.dp))

            inputField(
                label = "Тривалість планового простою (років)",
                textState = planDownTime,
                onValueChange = { planDownTime = it },
            )

            Spacer(modifier = Modifier.height(8.dp))

            inputField(
                label = "Збитки від аварійних вимкнень (грн/кВт*год)",
                textState = emergencyLoss,
                onValueChange = { emergencyLoss = it }
            )

            Spacer(modifier = Modifier.height(8.dp))

            inputField(
                label = "Збитки від планових вимкнень (грн/кВт*год)",
                textState = planLoss,
                onValueChange = { planLoss = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val _refuseFrequency = refuseFrequency.text.toDoubleOrNull() ?: 0.0
                    val _repairTime = repairTime.text.toDoubleOrNull() ?: 0.0
                    val _planDownTime = planDownTime.text.toDoubleOrNull() ?: 0.0
                    val _emergencyLoss = emergencyLoss.text.toDoubleOrNull() ?: 0.0
                    val _planLoss = planLoss.text.toDoubleOrNull() ?: 0.0

                    result = calculate(_refuseFrequency, _repairTime, _planDownTime, _emergencyLoss, _planLoss)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Обрахувати")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(String.format("Збитки від переривання електропостачання: ₴%.2f", result), fontSize = 20.sp)
        }
    }
}
