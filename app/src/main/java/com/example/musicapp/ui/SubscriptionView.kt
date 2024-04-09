package com.example.musicapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Subscription() {
    Column(modifier = Modifier.height(200.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Manage Subscriptions")
        Card(Modifier.padding(8.dp), elevation = CardDefaults.cardElevation(8.dp)) {
            Column(Modifier.padding(8.dp)) {
                Column {
                    Text(text = "Musical")
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(text = "Free Tier", modifier = Modifier.padding(top = 8.dp))
                        TextButton(onClick = { /*TODO*/ }) {
                            Row {
                                Text(text = "See All Plans")
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                    contentDescription = "See All Plans Buttons"
                                )
                            }
                        }

                    }

                }
            }
            HorizontalDivider(thickness = 1.dp, modifier = Modifier.padding(top = 8.dp, start = 4.dp, end = 4.dp), color = Color.White)
            Row(Modifier.padding(vertical = 16.dp, horizontal = 8.dp)) {
                Icon(imageVector = Icons.Default.AccountBox, contentDescription = "get a plan" )
                Text(text = "Get a Plan", modifier = Modifier.padding(start = 8.dp))
            }
        }
    }
}