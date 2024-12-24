package com.example.fooddelivery

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.clickable
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch


@Composable
fun Restaurants(navController: NavHostController, viewModel: RestaurantsViewModel = RestaurantsViewModel()) {
    val restaurants by viewModel.restaurants.observeAsState(initial = emptyList())
    val scope = rememberCoroutineScope()
    var city by remember { mutableStateOf("") }
//    LaunchedEffect(city) {
//        restaurants = FastApiClient.apiService.getRestaurants("рівне")
//    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Top Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.location),
                contentDescription = "Location Icon",
                tint = Color.Unspecified,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            BasicTextField(
                value = city,
                onValueChange =
                {
                    city = it
                    scope.launch { viewModel.fetchRestaurants(city) }
                },
//                textStyle = TextStyle(fontSize = 18.sp),
                modifier = Modifier
                    .weight(1f)
                    .background(Color.LightGray, shape = RoundedCornerShape(28.dp))
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
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
            text = "Доступні заклади",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
//            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 60.dp, top = 30.dp)
        )

        // List of restaurants
        LazyColumn {
            items(restaurants) { restaurant ->
                RestaurantCard(restaurant) { navController.navigate("menu/${restaurant.id}") }
            }
        }
    }
}

@Composable
fun RestaurantCard(restaurant: Restaurant, onClick: () -> Unit) {
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
        Column {
            Text(
                text = restaurant.name,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
//                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 10.dp)
            )
            ImageFromUrl("${FastApiClient.BASE_URL}/${restaurant.imageUrl}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp))
//            Image(
//                painter = painterResource(id = restaurant.imageRes),
//                contentDescription = "${restaurant.name} Image",
//                modifier = Modifier
//                    .height(180.dp)
//                    .fillMaxWidth()
//                    .clip(shape = RoundedCornerShape(12.dp))
//                    .align(Alignment.Start)
//            )

        }
    }
}

