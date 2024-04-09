package com.example.musicapp

import androidx.annotation.DrawableRes

sealed class Screens(val title: String, val route: String) {
    sealed class DrawerScreen(val dTitle: String, val dRoute: String, @DrawableRes val icon: Int) :
        Screens(dTitle, dRoute) {
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

    sealed class BottomScreen(bTitle: String, bRoute: String, @DrawableRes val icon: Int) :
        Screens(bTitle, bRoute) {
        data object Home : BottomScreen(
            "Home",
            bRoute = "home",
            R.drawable.baseline_home_24
        )

        data object Browse : BottomScreen(
            "Browse",
            bRoute = "browse",
            R.drawable.baseline_search_24
        )

        data object Library : BottomScreen(
            "Library",
            bRoute = "library",
            R.drawable.baseline_library_music_24
        )
    }
}
val screensInBottom = listOf(
    Screens.BottomScreen.Home,
    Screens.BottomScreen.Browse,
    Screens.BottomScreen.Library
)
val screensInDrawer = listOf(
    Screens.DrawerScreen.Account,
    Screens.DrawerScreen.Subscription,
    Screens.DrawerScreen.AddAccount
)