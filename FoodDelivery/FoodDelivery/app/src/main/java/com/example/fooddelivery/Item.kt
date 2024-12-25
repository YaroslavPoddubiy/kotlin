package com.example.fooddelivery

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch

@Composable
fun ItemInfo(navController: NavHostController, userViewModel: UserViewModel, itemId: Int, viewModel: ItemViewModel = ItemViewModel()) {
    val item by viewModel.item.observeAsState(Item(0, "", "", 0.0, 0, "static/no_photo.jpg"))
    val scope = rememberCoroutineScope()

    scope.launch {
        viewModel.getItemById(itemId)
    }


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Top Bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { navController.popBackStack() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF74C68F)),
                    modifier = Modifier.height(40.dp).clip(RoundedCornerShape(12.dp))
                ) {
                    Text(text = "Доступні заклади", style = MaterialTheme.typography.bodySmall)
                }
                Icon(
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = "Profile Icon",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(40.dp)
                        .clickable { navController.navigate("Profile") }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Food Name
            Text(
                text = item.name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            ImageFromUrl("${FastApiClient.BASE_URL}/${item.imageUrl}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(320.dp))

            Spacer(modifier = Modifier.height(16.dp))

            // Price and Add to Cart
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${item.price}₴",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Button(
                    onClick = { /* Handle Add to Cart */ },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
                ) {
                    Text(
                        text = "У кошик",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            if (item.ingredients.isNotEmpty()) {
                // Composition Section
                Text(
                    text = "Склад",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                Text(
                    text = item.ingredients, // Replace with actual composition details
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            }
        }
    }
}