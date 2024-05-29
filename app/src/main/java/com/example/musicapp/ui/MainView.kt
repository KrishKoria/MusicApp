package com.example.musicapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.primarySurface
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBarItem
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.musicapp.AccountDialog
import com.example.musicapp.MainViewModel
import com.example.musicapp.R
import com.example.musicapp.Screens
import com.example.musicapp.screensInBottom
import com.example.musicapp.screensInDrawer
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun MainView() {
    val drawerState = remember { DrawerState(DrawerValue.Closed) }
    val scope = rememberCoroutineScope()
    val viewModel: MainViewModel = viewModel()
    val currentScreen = remember {
        viewModel.selectedScreen
    }
    val sheetFullScreen by remember { mutableStateOf(false) }

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val dialogOpen = remember { mutableStateOf(false) }
    val title = remember { mutableStateOf(currentScreen.title) }
    val modalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = {
            it != ModalBottomSheetValue.HalfExpanded
        })
    val roundedCornerRadius = if (sheetFullScreen) 0.dp else 16.dp
    val bottomBar: @Composable () -> Unit = {
        if (currentScreen is Screens.DrawerScreen || currentScreen == Screens.BottomScreen.Home) {
            Card(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(bottom = 16.dp),
                shape = CircleShape,
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                BottomAppBar {
                    screensInBottom.forEach { screen ->
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    painter = painterResource(id = screen.icon),
                                    contentDescription = screen.title,
                                    Modifier.padding(end = 8.dp, top = 4.dp)
                                )
                            },
                            label = { Text(screen.title) },
                            selected = currentRoute == screen.route,
                            onClick = {
                                navController.navigate(screen.route)
                                title.value = screen.title
                            }
                        )
                    }
                }
            }
        }
    }
    ModalBottomSheetLayout(
        sheetState = modalBottomSheetState,
        sheetShape = RoundedCornerShape(
            topStart = roundedCornerRadius,
            topEnd = roundedCornerRadius
        ),
        sheetContent = {
            MoreBottomSheet()
        }
    ) {
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
                bottomBar = bottomBar,
                topBar = {
                    TopAppBar(title = { Text(text = title.value) },
                        actions = {
                            IconButton(onClick = {
                                scope.launch {
                                    if (modalBottomSheetState.isVisible) modalBottomSheetState.hide() else modalBottomSheetState.show()
                                }
                            }) {
                                Icon(
                                    imageVector = Icons.Default.MoreVert,
                                    contentDescription = "More"
                                )
                            }
                        },
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
                NavigationHandler(navController, it)
                AccountDialog(dialogOpen = dialogOpen)
            }
        }
    }
}

@Composable
fun MoreBottomSheet() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(color = MaterialTheme.colors.primarySurface)
    ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.SpaceBetween) {
            Row(modifier = Modifier.padding(16.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_settings_24),
                    contentDescription = "Settings",
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(text = "Settings", fontSize = 20.sp, color = Color.White)
            }
            Row(modifier = Modifier.padding(16.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_share_24),
                    contentDescription = "Share",
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(text = "Share", fontSize = 20.sp, color = Color.White)
            }
            Row(modifier = Modifier.padding(16.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.outline_help_center_24),
                    contentDescription = "Help",
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(text = "Help", fontSize = 20.sp, color = Color.White)
            }
        }
    }
}


@Composable
fun NavigationHandler(navController: NavHostController, pd: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = Screens.DrawerScreen.Account.dRoute,
        modifier = Modifier.padding(pd)
    ) {
        composable(Screens.BottomScreen.Home.route) {
            HomeView()
        }
        composable(Screens.BottomScreen.Browse.route) {
            BrowseScreen()
        }
        composable(Screens.BottomScreen.Library.route) {
            LibraryScreen()
        }
        composable(Screens.DrawerScreen.Account.dRoute) {
            AccountView()
        }
        composable(Screens.DrawerScreen.Subscription.dRoute) {
            Subscription()
        }
    }
}