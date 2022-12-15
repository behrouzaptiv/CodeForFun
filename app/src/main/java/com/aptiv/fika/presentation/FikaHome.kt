package com.aptiv.fika.presentation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun SetHomeScreen(name: String) {
    Text(text = "$name's Fika Week", color = Color.Black, fontSize = 30.sp)
}