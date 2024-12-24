package com.example.fooddelivery

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val formModifier = Modifier.fillMaxHeight(0.7f)
    .fillMaxWidth(0.7f)
    .background(color = Color(0xFFF0F0F0), shape = RoundedCornerShape(28.dp))

val inputModifier = Modifier.fillMaxWidth(0.9f)
    .border(1.dp, Color(0xFF74C68F), shape = RoundedCornerShape(28.dp))