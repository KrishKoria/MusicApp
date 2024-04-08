package com.example.musicapp.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.musicapp.AccountDialog
import com.example.musicapp.MainViewModel
import com.example.musicapp.Screens
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView() {
    val drawerState = remember { DrawerState(DrawerValue.Closed) }
    val scope = rememberCoroutineScope()
    val viewModel: MainViewModel = viewModel()
    val screensInDrawer = listOf(
        Screens.DrawerScreen.Account,
        Screens.DrawerScreen.Subscription,
        Screens.DrawerScreen.AddAccount
    )
    val currentScreen = remember {
        viewModel.selectedScreen
    }
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val dialogOpen = remember { mutableStateOf(false) }
    val title = remember { mutableStateOf(currentScreen.title) }
    ModalNavigationDrawer(drawerContent = {
        ModalDrawerSheet {
            LazyColumn(Modifier.padding(16.dp)) {
                items(screensInDrawer) { screen ->
                    NavigationDrawerItem(
                        icon = {
                            Icon(
                                painter = painterResource(id = screen.icon),
                                contentDescription = screen.dTitle,
                                Modifier.padding(end = 8.dp, top = 4.dp)
                            )
                        },
                        label = { Text(screen.title) },
                        selected = currentRoute == screen.route,
                        onClick = {
                            scope.launch {
                                drawerState.close()
                            }
                            if (screen.dRoute == "add_account") {
                                dialogOpen.value = true
                            } else {
                                navController.navigate(screen.dRoute)
                                title.value = screen.dTitle
                            }
                        }
                    )
                }
            }
        }
    }, drawerState = drawerState, gesturesEnabled = true) {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text(text = title.value) },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.AccountCircle,
                                contentDescription = "Menu"
                            )
                        }
                    })
            },
        ) {
            Navigation(navController, viewModel, it)
            AccountDialog(dialogOpen = dialogOpen)
        }
    }
}

@Composable
fun Navigation(navController: NavHostController, viewModel: MainViewModel, pd: PaddingValues) {
    NavHost(navController = navController, startDestination = Screens.DrawerScreen.Account.dRoute, modifier = Modifier.padding(pd)){
        composable(Screens.DrawerScreen.Account.dRoute) {
            AccountView()
        }
        composable(Screens.DrawerScreen.Subscription.dRoute) {
            Subscription()
        }
    }
}