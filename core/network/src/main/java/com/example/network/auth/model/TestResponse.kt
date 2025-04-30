package com.example.network.auth.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class TestResponse(
    val status : String,
    val message : String,
) : Parcelable
