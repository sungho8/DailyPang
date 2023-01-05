package com.confuse.dailypang

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient

class KakaoAuthViewModel() :ViewModel() {
    companion object{
        const val TAG : String = "KakaoLogin"
    }

//    private val context = application.applicationContext

}