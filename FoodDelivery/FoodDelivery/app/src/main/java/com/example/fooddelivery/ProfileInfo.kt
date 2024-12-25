package com.example.fooddelivery

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileInfo(navController: NavController, userViewModel: UserViewModel) {
    val user by userViewModel.user.observeAsState()
    var userName by remember { mutableStateOf(TextFieldValue("${user?.firstName}")) }
    var email by remember { mutableStateOf(TextFieldValue("${user?.lastName}")) }
    var phoneNumber by remember { mutableStateOf(TextFieldValue("${user?.email}")) }
    var address by remember { mutableStateOf(TextFieldValue("${user?.city}")) }


    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Profile") }
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
            TextField(
                value = userName,
                onValueChange = { userName = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(containerColor = Color(0xFFE6E6E6))
            )

            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(containerColor = Color(0xFFE6E6E6))
            )

            TextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = { Text("Phone Number") },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(containerColor = Color(0xFFE6E6E6))
            )

            TextField(
                value = address,
                onValueChange = { address = it },
                label = { Text("Address") },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(containerColor = Color(0xFFE6E6E6))
            )
        }
    }
}