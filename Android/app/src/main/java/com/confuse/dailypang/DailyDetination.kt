package com.confuse.dailypang

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink

// 인터페이스
interface DailyDestination {
    val icon: ImageVector
    val route: String   // navController에게 안내하기 위한 route string
}

/**
 * Daily app navigation destinations
 */
// 탭 정보
object Calendar : DailyDestination {
    override val icon = Icons.Filled.Home
    override val route = "calendar"
}

object Shop : DailyDestination {
    override val icon = Icons.Filled.ShoppingCart
    override val route = "accounts"
}

object Analyze : DailyDestination {
    override val icon = Icons.Filled.Info
    override val route = "bills"
}

object Profile : DailyDestination{
    override val icon = Icons.Filled.Person
    override val route = "profile"
}

object Login : DailyDestination{
    override val icon = Icons.Filled.Person
    override val route = "login"
}

val dailyTabRowScreens = listOf(Calendar,Shop,Analyze,Profile)