package com.aptiv.fika.presentation.theme

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextButton(text: String, onclick: () -> Unit,backgroundColor: Color = Color.White, textColor: Color = Color.Black) {
    Button(
        onClick = onclick,
        modifier = Modifier.width(120.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor)
    ) {
        Text(text = text, fontSize = 20.sp, color = textColor)
    }
}