package com.example.musicapp.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.musicapp.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeView() {
    val categories = listOf("Rock", "Pop", "Jazz", "Classical", "Hip Hop", "Rap", "Country", "Blues", "Metal", "Electronic")
    val grouped = listOf("New", "Popular", "Recommended").groupBy { it[0] }
    LazyColumn {
        grouped.forEach { (_, item) ->
            stickyHeader {
                Text(text = item[0], modifier = Modifier.padding(16.dp))
                LazyRow {
                    items(categories) { category ->
                        ItemCreator(category, drawable = R.drawable.baseline_music_note_24)
                    }
                }
            }
        }
    }
}

@Composable
fun ItemCreator(category: String, drawable: Int? = null) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .size(200.dp),
        border = BorderStroke(3.dp, Color.Black),
    ) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = category, modifier = Modifier.padding(16.dp))
        if (drawable != null) {
             Image(painter = painterResource(id = drawable), contentDescription = category)
        }
    }
    }
}