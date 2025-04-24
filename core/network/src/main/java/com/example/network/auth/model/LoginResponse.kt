package com.example.network.auth.model

import android.os.Parcelable
import androidx.core.app.NotificationCompat
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize




//@Parcelize
//data class LoginResponse(
//    val status : String,
//    val message : String,
//    val data : LoginData
//) : Parcelable



@Parcelize
data class LoginResponse(
    @SerializedName("refresh_token")
    val refreshToken: String,
    @SerializedName("access_token")
    val accessToken: String
) : Parcelable
