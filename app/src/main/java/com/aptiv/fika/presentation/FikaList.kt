package com.aptiv.fika.presentation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aptiv.fika.R
import com.aptiv.fika.presentation.viewmodel.PersonViewModel

@Composable
fun SetFikaList(personViewModel: PersonViewModel = hiltViewModel()) {
    val result = personViewModel.state.collectAsState()
    val status = personViewModel.loadingState.collectAsState()

    val imageList = mutableListOf<Int>()
    imageList.add(R.drawable.aloe_vera)
    imageList.add(R.drawable.calendula)
    imageList.add(R.drawable.cilantro)
    imageList.add(R.drawable.cucumber)
    imageList.add(R.drawable.chamomile)
    imageList.add(R.drawable.jojoba)
    imageList.add(R.drawable.kiwi)
    imageList.add(R.drawable.rose)

    LazyColumn(
        modifier = Modifier.background(Color.LightGray).fillMaxWidth().fillMaxHeight()
    ) {
        items(result.value.size) { index ->
            val resultItem = result.value[index]
            Card(
                modifier = Modifier.padding(8.dp).fillMaxWidth().wrapContentHeight(),
                elevation = 2.dp,
                backgroundColor = Color.Black,
                shape = RoundedCornerShape(corner = CornerSize(16.dp))
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = imageList[index]),
                        contentDescription = null,
                        modifier = Modifier.size(130.dp).padding(8.dp).weight(0.5f),
                        contentScale = ContentScale.Fit
                    )

                    Column(
                        Modifier.padding(8.dp).weight(0.5f),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            resultItem.person.name,
                            style = MaterialTheme.typography.h3
                        )

                        val date = if (resultItem.eventList.isNotEmpty()) {
                            resultItem.eventList.first().date.toString()
                        } else "12/12/2022"

                        Log.d(
                            "TAG",
                            "SetFikaList() called $date"
                        )
                        Text(
                            date,
                            style = MaterialTheme.typography.h6
                            /* modifier = Modifier.padding(16.dp).width(25.dp)*/

                        )

                        /*Text(
                            text = name,
                            style = MaterialTheme.typography.h4,
                            color = MaterialTheme.colors.onSurface,
                        )
                        Text(
                            text = description,
                            style = MaterialTheme.typography.body2,
                        )*/
                    }
                }
            }
        }
    }
}
