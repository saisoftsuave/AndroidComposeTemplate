package com.example.network.product.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


data class Product(
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    val image: String,
    val category_id: Int,
    val images: List<String>,
    val reviews: List<Review>,
    val inventories: List<Inventory>,
    val cart: List<CartItem>,
    val order_items: List<OrderItem>
)

// Placeholder classes for nested lists; define fields as needed based on your API schema
data class Review(
    val id: String
)

data class Inventory(
    val id: String
)

data class CartItem(
    val id: String
)

data class OrderItem(
    val id: String
)
