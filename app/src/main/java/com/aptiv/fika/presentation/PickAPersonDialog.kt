package com.aptiv.fika.presentation

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.aptiv.fika.domain.entity.Person
import com.aptiv.fika.domain.entity.PersonAndEvent
import com.aptiv.fika.presentation.theme.TextButton
import com.aptiv.fika.presentation.theme.type
import com.aptiv.fika.presentation.viewmodel.PersonViewModel

@Composable
fun PickAPersonDialog(
    viewModel: PersonViewModel,
    onPersonPicked: (person: Person) -> Unit) {
    val context: Context = LocalContext.current
    val dialogState = remember { mutableStateOf(false) }
    val personsAndEvents = remember { mutableStateOf(viewModel.state.value) }

    if(dialogState.value) {
        Dialog(
            onDismissRequest = { dialogState.value = false },
            content = {
                Card(
                    modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                    shape = RoundedCornerShape(8.dp)) {
                    Column(
                        modifier = Modifier.fillMaxWidth().fillMaxHeight(0.9f),
                        verticalArrangement = Arrangement.SpaceBetween
                    ){
                        TitleAndButton("Pick person", dialogState)
                        Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.Top) {
                            personsAndEvents.value.forEach { personAndEvent ->
                                val person = personAndEvent.person
                                TextButton(
                                    text = person.name,
                                    onclick = {
                                        onPersonPicked(person)
                                        dialogState.value = false
                                    }
                                )
                            }
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .fillMaxWidth(1f)
                                .padding(20.dp),
                            horizontalArrangement = Arrangement.End
                        ) {
                            CreateAPersonDialog { name ->
                                viewModel.addPerson(name)
                                personsAndEvents.value = viewModel.state.value
                            }
                        }
                    }
                }
            },
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            )
        )
    } else {
        Toast.makeText(context, "Dialog Closed", Toast.LENGTH_SHORT).show()
    }
    ExtendedFloatingActionButton(
        icon = { Icon(Icons.Filled.Add,"", tint = Black) },
        text = {Text("Add Person", color = Black, fontSize = 32.sp)},
        onClick = {
            dialogState.value = true },
        backgroundColor = White
    )
}

@Composable
private fun TitleAndButton(title: String, dialogState: MutableState<Boolean>) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = title, fontSize = 24.sp)
            IconButton(
                modifier = Modifier.then(Modifier.size(24.dp)),
                onClick = { dialogState.value = false }) {
                Icon(Icons.Filled.Close, "contentDescription")
            }
        }
        Divider(color = DarkGray, thickness = 1.dp)
    }
}