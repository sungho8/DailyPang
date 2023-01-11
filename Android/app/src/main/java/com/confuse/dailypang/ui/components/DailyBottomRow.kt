package com.confuse.dailypang.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.confuse.dailypang.DailyApp
import com.confuse.dailypang.DailyDestination
import java.util.*

// 하단바
@Composable
fun DailyBottomRow(
    allScreens: List<DailyDestination>,         // 바텀바 구성요소
    onTabSelected: (DailyDestination) -> Unit,  // 바텀바 클릭시 이벤트
    currentScreen: DailyDestination             // 현재 선택된 스크린
){
    Surface(
        modifier = Modifier.height(TabHeight)
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.selectableGroup()){
            allScreens.forEach { screen ->
                DailyTab(
                    text = screen.route,
                    icon = screen.icon,
                    onSelected = { onTabSelected(screen) },
                    selected = currentScreen == screen
                )
            }
        }
    }
}

@Composable
fun DailyTab(
    text: String,
    icon: ImageVector,
    onSelected: ()-> Unit,
    selected: Boolean
){
    val color = MaterialTheme.colors.onSurface
    val durationMillis = if(selected) TabFadeInAnimationDuration else TabFadeOutAnimationDuration

    // fade in out
    val animSpec = remember {
        tween<Color>(
            durationMillis = durationMillis,
            easing = LinearEasing,
            delayMillis = TabFadeInAnimationDelay
        )
    }
    // 선택시 색깔
    val tabTintColor by animateColorAsState(
        targetValue = if (selected) color else color.copy(alpha = InactiveTabOpacity),
        animationSpec = animSpec
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
            .animateContentSize()
            .height(TabHeight)
            .selectable(
                selected = selected,
                onClick = onSelected,
                role = Role.Tab,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(
                    bounded = false,
                    radius = Dp.Unspecified,
                    color = Color.Unspecified
                )
            )
            .clearAndSetSemantics { contentDescription = text }
    ) {
        Icon(imageVector = icon, contentDescription = text, tint = tabTintColor)
        if (selected) {
            Text(text.uppercase(Locale.getDefault()), color = tabTintColor)
        }
    }
}


private val TabHeight = 156.dp
private const val InactiveTabOpacity = 0.60f

private const val TabFadeInAnimationDuration = 100
private const val TabFadeInAnimationDelay = 100
private const val TabFadeOutAnimationDuration = 100