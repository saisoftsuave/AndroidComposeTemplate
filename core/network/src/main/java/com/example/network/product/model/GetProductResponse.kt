package com.example.network.product.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class Product(
    val id: String,
    val name: String,
    val description: String,
    val price: Float,
    @SerializedName("category_id")
    val categoryId: Int,
    val image: String
) : Parcelable

