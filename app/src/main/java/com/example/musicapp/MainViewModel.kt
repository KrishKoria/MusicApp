package com.example.musicapp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _selectedScreen: MutableState<Screens> = mutableStateOf(Screens.DrawerScreen.Account)
    val selectedScreen get() = _selectedScreen.value
    fun setSelectedScreen(screen: Screens) {
        _selectedScreen.value = screen
    }
}