package com.example.fooddelivery

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController



//data class Item(val name: String, val price: String, val imageRes: Int)

@Composable
fun Cart(navController: NavHostController) {
    val Items = remember {
        listOf(
            Item(1, "Біг мак меню", "", 230.0, 1, "static/restaurants/bigmacmenu.jpg"),
            Item(2, "Дабл Роял Чізбургер Меню", "", 170.0, 1, "static/no_photo.jpg")
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { navController.popBackStack() },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF74C68F)),
                modifier = Modifier.height(40.dp).clip(RoundedCornerShape(12.dp))
            ) {
                Text(text = "Доступні заклади", style = MaterialTheme.typography.bodySmall)
            }
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(id = R.drawable.user),
                contentDescription = "Profile Icon",
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(40.dp)
                    .clickable { navController.navigate("Profile") }
            )
        }

        // Title
        Text(
            text = "Меню McDonald's",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
//            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 60.dp, top = 30.dp)
        )

        // Menu list
        LazyColumn {
            items(Items) { Item ->
                CartItemCard(Item)
            }
        }

        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF74C68F)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color(0xFF74C68F), shape = RoundedCornerShape(12.dp))
        ) {
            Text(text = "Замовити", color = Color.White)
        }
    }
}

@Composable
fun CartItemCard(item: Item) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .background(color = Color.White)
            .clickable { /* Add to cart logic */ },
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Image
//            Image(
//                painter = painterResource(id = Item.imageRes),
//                contentDescription = "${Item.name} Image",
//                modifier = Modifier
//                    .size(100.dp)
//                    .padding(end = 16.dp)
//            )

            // Details
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = item.name,
//                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = item.price.toString(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal,
//                    style = MaterialTheme.typography.bodyMedium
                )
            }

        }
    }
}