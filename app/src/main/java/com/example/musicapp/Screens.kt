package com.example.musicapp

import androidx.annotation.DrawableRes

sealed class Screens(val title: String, val route: String) {
    sealed class DrawerScreen(val dTitle: String, val dRoute: String, @DrawableRes val icon: Int) : Screens(dTitle, dRoute) {
        data object Account : DrawerScreen(
            "Account",
            dRoute = "account",
            R.drawable.ic_account
        )
        data object Subscription : DrawerScreen(
            "Subscription",
            dRoute = "subscription",
            R.drawable.ic_subscribe
        )
        data object AddAccount : DrawerScreen(
            "Add Account",
            dRoute = "add_account",
            R.drawable.baseline_person_add_alt_1_24
        )
    }
}