package com.example.fooddelivery

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch

@Composable
fun Login(navController: NavHostController, userViewModel: UserViewModel) {
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val user by userViewModel.user.observeAsState()
    val errorMessage by userViewModel.error.observeAsState()
    val scope = rememberCoroutineScope()
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(16.dp),
            colors = CardDefaults.cardColors(contentColor = Color(0xFFEFEFEF))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Заголовок
                Text(
                    text = "Вхід в акаунт",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF74C68F))
                        .padding(vertical = 16.dp),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Поле логіну
                OutlinedTextField(
                    value = login,
                    onValueChange = {login = it},
                    label = { Text("Логін") },
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Color(0xFF74C68F), unfocusedBorderColor = Color(0xFF74C68F))
                )

                // Поле паролю
                OutlinedTextField(
                    value = password,
                    onValueChange = {password = it},
                    label = { Text("Пароль") },
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Color(0xFF74C68F), unfocusedBorderColor = Color(0xFF74C68F))
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "$errorMessage")

                // Кнопка входу
                Button(
                    onClick =
                    {
                        scope.launch {
                            userViewModel.login(LoginRequest(login, password))
                        }
                        if (user != null) {
                            navController.navigate("restaurants")
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF74C68F)),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Увійти", color = Color.White)
                }
            }
        }
    }
}