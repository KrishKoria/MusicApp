package com.example.musicapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.musicapp.Lib
import com.example.musicapp.libraries

@Composable
fun LibraryScreen() {
    LazyColumn {
        items(libraries) { library ->
            LibraryItem(library = library)}
    }
}

@Composable
fun LibraryItem(library: Lib) {
    Column {
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Icon(painter = painterResource(id = library.icon), contentDescription = library.title, modifier = Modifier.padding(horizontal = 8.dp))
                Text(text = library.title)
            }
            Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "Arrow Right")
        }
    }
    HorizontalDivider(color = Color.Gray, thickness = 1.dp)
}
