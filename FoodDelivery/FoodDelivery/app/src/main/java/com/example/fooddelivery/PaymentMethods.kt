package com.example.fooddelivery

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentMethods(navController: NavHostController, userViewModel: UserViewModel) {
    var paymentMethods by remember { mutableStateOf(listOf("Visa **** 1234", "MasterCard **** 5678")) }
    var newPaymentMethod by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Payment Methods") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Button(
                onClick = { navController.popBackStack() },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF74C68F)),
                modifier = Modifier.height(40.dp).clip(RoundedCornerShape(12.dp))
            ) {
                Text(text = "Доступні заклади", style = MaterialTheme.typography.bodySmall)
            }
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(paymentMethods.size) { index ->
                    PaymentMethodItem(
                        paymentMethod = paymentMethods[index],
                        onDelete = {
                            paymentMethods = paymentMethods.filterIndexed { i, _ -> i != index }
                        }
                    )
                }
            }

            TextField(
                value = newPaymentMethod,
                onValueChange = { newPaymentMethod = it },
                label = { Text("Add Payment Method") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    if (newPaymentMethod.isNotBlank()) {
                        paymentMethods = paymentMethods + newPaymentMethod
                        newPaymentMethod = ""
                    }
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Add")
            }
        }
    }
}

@Composable
fun PaymentMethodItem(paymentMethod: String, onDelete: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = paymentMethod, style = MaterialTheme.typography.bodyLarge)
        IconButton(onClick = onDelete) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
        }
    }
}