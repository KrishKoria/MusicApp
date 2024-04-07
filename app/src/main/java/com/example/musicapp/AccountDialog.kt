package com.example.musicapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties

@Composable
fun AccountDialog(dialogOpen: MutableState<Boolean>) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    if (dialogOpen.value) {
        AlertDialog(
            onDismissRequest = { dialogOpen.value = false },
            confirmButton = {
                OutlinedButton(onClick = {
                    dialogOpen.value = false

                }) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                OutlinedButton(onClick = {
                    dialogOpen.value = false
                }) {
                    Text("Dismiss")
                }
            },
            title = {
                Text("Account")
            },
            text = {
                Column(
                    Modifier
                        .wrapContentSize()
                        .padding(top = 16.dp), verticalArrangement = Arrangement.Center
                ) {
                    TextField(
                        value = email,
                        onValueChange = {
                            email = it
                        },
                        Modifier.padding(top = 16.dp),
                        label = {
                            Text("Email")
                        },
                        placeholder = {
                            Text("Enter your email")
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                    )
                    TextField(
                        value = password,
                        onValueChange = {
                            password = it
                        },
                        Modifier.padding(top = 16.dp),
                        label = {
                            Text("Password")
                        },
                        placeholder = {
                            Text("Enter your Password")
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            shape = RoundedCornerShape(10.dp),
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            )

        )
    }
}
