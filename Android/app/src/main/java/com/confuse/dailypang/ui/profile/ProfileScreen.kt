package com.confuse.dailypang.ui.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun ProfileScreen(
    onLoginButton:() -> Unit = {},
) {
    Column() {
        Text(text = "프로필")
        
        Button(onClick = {
            onLoginButton()
        }) {
            Text(text = "로그인")
        }
    }
}