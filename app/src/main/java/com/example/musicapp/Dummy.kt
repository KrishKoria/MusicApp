package com.example.musicapp

import androidx.annotation.DrawableRes

data class Lib(@DrawableRes val icon: Int, val title: String)

val libraries = listOf(
    Lib(R.drawable.baseline_music_note_24, "Songs"),
    Lib(R.drawable.ic_microphone, "Artists"),
    Lib(R.drawable.ic_baseline_album_24, "Albums"),
    Lib(R.drawable.ic_playlist_green, "Playlist"),
    Lib(R.drawable.ic_genre, "Genre")
)
