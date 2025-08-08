package com.grensil.cleanarchitecturestudy

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.grensil.home.HomeScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomNavigation(navController = navController) }
    ) { innerPadding ->
        Box(Modifier.fillMaxSize().padding(bottom = innerPadding.calculateBottomPadding())) {
            MainNavGraph(
                navController = navController
            )
        }
    }
}

@Composable
fun BottomNavigation(navController: NavHostController) {
    val tabs = listOf(
        TabScreen.Home,
        TabScreen.Search,
        TabScreen.Profile
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(modifier = Modifier.fillMaxWidth().height(60.dp)) {
        tabs.forEach { tab ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = tab.icon,
                        contentDescription = tab.title
                    )
                },
                label = { Text(tab.title) },
                selected = currentRoute == tab.route,
                onClick = {
                    navController.navigate(tab.route) {
                        // 백스택 정리 - 같은 탭 중복 방지
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // 탭 재선택시 동일한 인스턴스 재사용
                        launchSingleTop = true
                        // 상태 복원
                        restoreState = true
                    }
                }
            )
        }
    }

}

@Composable
fun MainNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = TabScreen.Home.route
    ) {
        composable(TabScreen.Home.route) {
            HomeScreen()
        }
        composable(TabScreen.Search.route) {
            //SearchScreen(navController)
        }
        composable(TabScreen.Profile.route) {
            //ProfileScreen(navController)
        }
    }
}