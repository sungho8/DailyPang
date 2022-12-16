package com.confuse.dailypang

import android.content.res.Resources.Theme
import android.graphics.drawable.ColorStateListDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.confuse.dailypang.ui.theme.DailyPangTheme
import java.time.LocalDate
import java.util.Calendar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DailyPangTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Calendar()
                }
            }
        }
    }
}

@Composable
fun Calendar(){
    val month = 10
    val firstDayWeek = firstDayWeek(2022, month)
    Column(modifier = Modifier.size(500.dp)) {
        for(i in 0 until 6){
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = if(i < 3) Arrangement.End else Arrangement.Start) {
                for (j in 1 .. 7){
                    DateBlock(month,i * 7 + j - firstDayWeek + 1,8,4)
                }
            }
        }
    }
}

@Composable
fun DateBlock(month : Int,day : Int, vertical : Int, horizontal : Int){
    val monthList = listOf(31,28,31, 30,31,30, 31,31,30, 31,30,31)
    val visible = day in 1..monthList[month]
    DailyPangTheme {
        AnimatedVisibility(visible = visible) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = horizontal.dp)
            )
            {
                Text(modifier = Modifier.padding(vertical = vertical.dp), text = "${day}")
                Box(
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp)
                        .background(color = MaterialTheme.colors.onSurface, shape = CircleShape)
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Calendar()
}

@Preview(showBackground = true)
@Composable
fun DatePreview(){
    DailyPangTheme {
        DateBlock(10,14,8,4)
    }
}