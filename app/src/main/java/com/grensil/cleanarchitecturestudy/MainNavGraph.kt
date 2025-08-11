package com.grensil.cleanarchitecturestudy

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.grensil.detail.DetailScreen
import com.grensil.domain.dto.AnimeDto
import com.grensil.favorite.FavoriteScreen
import com.grensil.home.HomeScreen
import java.net.URLDecoder

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val showBottomBarRoutes = listOf(
        TabScreen.Home.route,
        TabScreen.Search.route,
        TabScreen.Profile.route
    )
    val shouldShowBottomBar = currentRoute in showBottomBarRoutes

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (shouldShowBottomBar) {
                BottomNavigation(navController = navController)
            } else {
                Box(modifier = Modifier.fillMaxWidth().height(60.dp))
            }
        }
    ) { innerPadding ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(
                    bottom = innerPadding.calculateBottomPadding(),
                    top = innerPadding.calculateTopPadding()
                )
        ) {
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

    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        tabs.forEach { tab ->
            NavigationBarItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                icon = {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        imageVector = tab.icon,
                        contentDescription = tab.title
                    )
                },
                label = { Text(text = tab.title, fontSize = 12.sp) },
                selected = currentRoute == tab.route,
                onClick = {
                    navController.navigate(tab.route) {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
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
            HomeScreen(navController = navController)
        }
        composable(TabScreen.Search.route) {
            FavoriteScreen(navController = navController)
        }
        composable(TabScreen.Profile.route) {
            //ProfileScreen(navController = navController)
        }

        composable(
            route = "detail/{animeId}/{animeName}/{animeImg}/{animeBookmarked}",
            arguments = listOf(
                navArgument("animeId") { type = NavType.IntType },
                navArgument("animeName") { type = NavType.StringType },
                navArgument("animeImg") { type = NavType.StringType },
                navArgument("animeBookmarked") { type = NavType.BoolType }
            )) { backStackEntry ->

            val animeId = backStackEntry.arguments?.getInt("animeId") ?: 0
            val animeName =
                URLDecoder.decode(backStackEntry.arguments?.getString("animeName") ?: "", "UTF-8")
            val animeImg =
                URLDecoder.decode(backStackEntry.arguments?.getString("animeImg") ?: "", "UTF-8")
            val animeBookmarked = backStackEntry.arguments?.getBoolean("animeBookmarked") ?: false

            val intentAnimeDto = AnimeDto(
                anime_id = animeId,
                anime_name = animeName,
                anime_img = animeImg,
                bookmarked = animeBookmarked
            )

            DetailScreen(navController = navController, animeDto = intentAnimeDto)
        }
    }
}