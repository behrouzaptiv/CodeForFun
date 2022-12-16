package com.aptiv.fika.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aptiv.fika.R

@Composable
fun SetHomeScreen(name: String) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val imageModifier = Modifier.weight(1f, fill = true)
            .aspectRatio(16f / 12f)
            .fillMaxSize()
        Image(
            painter = painterResource(id = R.drawable.team),
            contentDescription = "logo",
            contentScale = ContentScale.Crop,
            modifier = imageModifier
        )
        Column(
            Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Card(
                backgroundColor = Color.Transparent,
                shape = RoundedCornerShape(100.dp),
            ){
                Column(Modifier.padding(10.dp)) {
                    Text(
                        text = "$name's Fika Week",
                        color = Color.White,
                        fontSize = 100.sp)
                }
            }
        }
        Image(
            painter = painterResource(id = R.drawable.team),
            contentDescription = "logo",
            contentScale = ContentScale.Crop,
            modifier = imageModifier
        )
    }
    Image(
        painter = painterResource(id = R.drawable.team),
        contentDescription = "logo",
        contentScale = ContentScale.Fit,
    )

    // this variable use to handle list state
    val notesList = remember {
        mutableStateListOf<String>()
    }
  // this variable use to handle edit text input value
    val inputvalue = remember { mutableStateOf(TextFieldValue()) }
    Column {
        Row(
            Modifier
                .fillMaxWidth()
                .height(Dp(60f))
        ) {
            TextField(
                value = inputvalue.value,
                onValueChange = {
                    inputvalue.value = it
                },
                modifier = Modifier.weight(0.8f),
                placeholder = { Text(text = "Enter your name", color = Color.White) },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = true, keyboardType = KeyboardType.Text, imeAction = ImeAction.Done
                ),
                textStyle = TextStyle(
                    color = Color.White, fontSize = TextUnit.Unspecified,
                    fontFamily = FontFamily.SansSerif,
                ),
                maxLines = 1,
                singleLine = true

            )

            Button(
                onClick = {
                    notesList.add(inputvalue.value.text)
                },
                modifier = Modifier
                    .weight(0.2f)
                    .fillMaxHeight()
            ) {
                Text(text = "JOIN")
            }
        }
        Spacer(modifier = Modifier.height(Dp(1f)))
        Surface(modifier = Modifier.padding(all = Dp(5f))) {
            LazyColumn {
                itemsIndexed(notesList) { index, item ->
                    val annotatedText = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Color.Blue,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("Delete")
                        }
                    }
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .height(Dp(30f))
                    ) {
                        Text(text = item, Modifier.weight(0.85f))
                        ClickableText(
                            text = annotatedText, onClick = {
                                notesList.remove(item)
                            },
                            modifier = Modifier.weight(0.15f)
                        )
                    }
                }
            }
        }
    }
}

