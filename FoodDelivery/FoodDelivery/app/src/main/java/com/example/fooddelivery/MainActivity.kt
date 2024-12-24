package com.example.fooddelivery


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Main()
//            Test()
        }
    }

}


@Composable
fun Main(){
    fun userIsAuthenticated() : Boolean{
        return false
    }

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { Login(navController) }
        composable("restaurants") { Restaurants(navController) }
        composable("menu/{restaurantId}", listOf()) { navBackStackEntry ->
            val restaurantId = navBackStackEntry.arguments?.getString("restaurantId")?.toIntOrNull() ?: 0
            Menu(navController, restaurantId)
        }
        composable("item/{itemId}") { navBackStackEntry ->
            val item_id = navBackStackEntry.arguments?.getString("itemId")?.toIntOrNull() ?: 0
            ItemInfo(navController, item_id)
        }
        composable("profile") { Profile(navController) }
        composable ( "profile_information") { ProfileInfo(navController) }
        composable ( "payment_methods") { PaymentMethods(navController) }
        composable ( "orders") { Orders(navController) }
        composable ( "cart") { Cart(navController) }
    }

    if (userIsAuthenticated()) {
        navController.navigate("restaurants")
    } else {
        navController.navigate("login")
    }

}



