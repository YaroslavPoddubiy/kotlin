package com.example.pr5

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
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                MainScreen()
            }
        }
    }
}


@Composable
fun MainScreen() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { Menu(navController) }
        composable("screen1") { Task1(navController) }
        composable("screen2") { Task2(navController) }
    }
}

@Composable
fun Menu(navController: NavController) {
    Column(modifier = Modifier.
    fillMaxSize().
    wrapContentSize(align = Alignment.Center)
    ) {
        Button(onClick = { navController.navigate("screen1") }) {
            Text(text = "Завдання 1")
        }
        Button(onClick = { navController.navigate("screen2") }) {
            Text(text = "Завдання 2")
        }
    }
}