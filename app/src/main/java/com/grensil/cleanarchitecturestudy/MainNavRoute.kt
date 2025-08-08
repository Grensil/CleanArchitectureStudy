package com.grensil.cleanarchitecturestudy

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class TabScreen(val route: String, val title: String, val icon: ImageVector) {
    object Home : TabScreen("home", "홈", Icons.Default.Home)
    object Search : TabScreen("search", "검색", Icons.Default.Search)
    object Profile : TabScreen("profile", "프로필", Icons.Default.Person)
}