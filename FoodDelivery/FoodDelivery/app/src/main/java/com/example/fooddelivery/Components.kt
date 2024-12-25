package com.example.fooddelivery

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter

@Composable
fun inputField(
    label: String,
    textState: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = textState,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = modifier,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        )
    )
}

@Composable
fun ImageFromUrl(imageUrl: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
        )
    }
}

@Composable
fun ItemCard(screen: String, item: Item, onClick: () -> Unit, onButtonClick: () -> Unit = {}) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .background(color = Color.White)
            .clickable { onClick() },
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
            ImageFromUrl("${FastApiClient.BASE_URL}/${item.imageUrl}",
                modifier = Modifier
                    .width(150.dp)
                    .height(80.dp))

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
                    text = "${item.price}₴",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal,
//                    style = MaterialTheme.typography.bodyMedium
                )
            }

            if (screen == "menu"){
                // Add to cart button
                Button(
                    onClick = onButtonClick,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF74C68F))
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.white_cart),
                        contentDescription = "Add to Cart",
                        modifier = Modifier.size(25.dp)
                    )
                }
            }
            else {
                IconButton(onClick = onButtonClick) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
                }
            }

        }
    }
}
