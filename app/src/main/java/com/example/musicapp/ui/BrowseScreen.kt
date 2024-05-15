package com.example.musicapp.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.musicapp.R


@Composable
fun BrowseScreen() {
    val categories = listOf("Rock", "Pop", "Jazz", "Classical", "Hip Hop", "Rap", "Country", "Blues", "Metal", "Electronic")
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(categories) { category ->
            ItemCreator(category, drawable = R.drawable.baseline_music_note_24)
        }
    }
}
