package com.confuse.dailypang

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.confuse.dailypang.ui.analyze.AnalyzeScreen
import com.confuse.dailypang.ui.calendar.CalendarScreen
import com.confuse.dailypang.ui.components.DailyBottomRow
import com.confuse.dailypang.ui.login.LoginScreen
import com.confuse.dailypang.ui.profile.ProfileScreen
import com.confuse.dailypang.ui.shop.ShopScreen
import com.confuse.dailypang.ui.theme.DailyPangTheme
import java.security.MessageDigest


class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            val information =
                packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNING_CERTIFICATES)
            val signatures = information.signingInfo.apkContentsSigners
            val md = MessageDigest.getInstance("SHA")
            for (signature in signatures) {
                val md: MessageDigest
                md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                var hashcode = String(Base64.encode(md.digest(), 0))
                Log.d("hashcode", "" + hashcode)
            }
        } catch (e: Exception) {
            Log.d("hashcode", "에러::" + e.toString())

        }

        setContent {
            DailyPangTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    DailyApp()
                }
            }
        }
    }
}

@Composable
fun DailyApp(){
    DailyPangTheme {
        // navHost 에게 넘겨줄 navController
        val navController = rememberNavController()

        //
        val currentBackStack by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStack?.destination

        val currentRoute = currentDestination?.route
        val currentScreen = dailyTabRowScreens.find { it.route == currentRoute } ?: Calendar

        Scaffold(
            bottomBar = {
                if(currentRoute != Login.route)
                    DailyBottomRow(
                        allScreens = dailyTabRowScreens,
                        onTabSelected = {newScreen -> navController.navigateSingleTopTo(newScreen.route)},
                        currentScreen = currentScreen
                    )
            }
        ) { innerPadding ->
            DailyNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun DailyNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    // 각 composable 에 route 로 접근
    NavHost(
        navController = navController,
        startDestination = Calendar.route,
        modifier = modifier
    ) {
        composable(route = Calendar.route) {
            CalendarScreen()
        }
        composable(route = Shop.route) {
            ShopScreen()
        }
        composable(route = Analyze.route) {
            AnalyzeScreen()
        }
        composable(route = Profile.route){
            ProfileScreen(onLoginButton = { navController.navigateSingleTopTo(Login.route) })
        }
        composable(route = Login.route){
            val kakaoAuthViewModel :KakaoAuthViewModel = viewModel()
            LoginScreen(kakaoAuthViewModel)
        }
    }
}

//// 전체적인 달력 틀
//@Composable
//fun Calendar(){
//    val month = 10
//    val firstDayWeek = firstDayWeek(2022, month)
//    Column(modifier = Modifier.size(500.dp)) {
//        for(i in 0 until 6){
//            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = if(i < 3) Arrangement.End else Arrangement.Start) {
//                for (j in 1 .. 7){
//                    DateBlock(month,i * 7 + j - firstDayWeek + 1,8,4)
//                }
//            }
//        }
//    }
//}
//
//// 달력에서 하루
//@Composable
//fun DateBlock(month : Int,day : Int, vertical : Int, horizontal : Int){
//    val monthList = listOf(31,28,31, 30,31,30, 31,31,30, 31,30,31)
//    val visible = day in 1..monthList[month]
//    DailyPangTheme {
//        AnimatedVisibility(visible = visible) {
//            Column(
//                horizontalAlignment = Alignment.CenterHorizontally,
//                modifier = Modifier.padding(horizontal = horizontal.dp)
//            )
//            {
//                Text(modifier = Modifier.padding(vertical = vertical.dp), text = "${day}")
//                Box(
//                    modifier = Modifier
//                        .width(50.dp)
//                        .height(50.dp)
//                        .background(color = MaterialTheme.colors.onSurface, shape = CircleShape)
//                )
//            }
//        }
//
//    }
//}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DailyApp()
}

fun NavHostController.navigateSingleTopTo(route: String) = this.navigate(route) { launchSingleTop = true }