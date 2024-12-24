package com.example.fooddelivery

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Profile(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Button(
            onClick = { navController.popBackStack() },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF74C68F)),
            modifier = Modifier.height(40.dp).clip(RoundedCornerShape(12.dp))
        ) {
            Text(text = "Доступні заклади", style = MaterialTheme.typography.bodySmall)
        }
        Text(text = "Ім'я Користувача", fontSize = 32.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(bottom = 60.dp, top = 30.dp))
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
                .clickable {
                    navController.navigate("profile_information")
                }
        )
        {
            Icon(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Location Icon",
                tint = Color.Unspecified,
                modifier = Modifier.size(40.dp)
            )
            Text(text = "Профіль", fontSize = 24.sp, modifier = Modifier.padding(top = 6.dp, start = 10.dp))
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
                .clickable {
                    navController.navigate("payment_methods")
                }
        )
        {
            Icon(
                painter = painterResource(id = R.drawable.credit_card),
                contentDescription = "Location Icon",
                tint = Color.Unspecified,
                modifier = Modifier.size(40.dp)
            )
            Text(text = "Способи оплати", fontSize = 24.sp, modifier = Modifier.padding(top = 6.dp, start = 10.dp))
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
                .clickable {
                    navController.navigate("orders")
                }
        )
        {
            Icon(
                painter = painterResource(id = R.drawable.clipboard),
                contentDescription = "Location Icon",
                tint = Color.Unspecified,
                modifier = Modifier.size(40.dp)
            )
            Text(text = "Мої замовлення", fontSize = 24.sp, modifier = Modifier.padding(top = 6.dp, start = 10.dp))
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
                .clickable {
                    navController.navigate("cart")
                }
        )
        {
            Icon(
                painter = painterResource(id = R.drawable.shopping_cart),
                contentDescription = "Location Icon",
                tint = Color.Unspecified,
                modifier = Modifier.size(40.dp)
            )
            Text(text = "Кошик", fontSize = 24.sp, modifier = Modifier.padding(top = 6.dp, start = 10.dp))
        }

        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = { navController.popBackStack() },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color.Red, shape = RoundedCornerShape(12.dp))
        ) {
            Text(text = "Вийти", color = Color.White)
        }
    }
}