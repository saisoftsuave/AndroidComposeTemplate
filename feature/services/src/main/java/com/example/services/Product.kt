package com.example.services

data class Product(
    val productImage: String,
    val productName: String,
    val ratting: Int, // Assuming rating is from 0 to 5
    val price: Float
)

// Sample list of products
val sampleProducts = listOf(
    Product(
        productImage = "https://example.com/images/laptop.jpg",
        productName = "Laptop Pro",
        ratting = 4,
        price = 999.99f
    ),
    Product(
        productImage = "https://example.com/images/smartphone.jpg",
        productName = "Smartphone X",
        ratting = 5,
        price = 699.50f
    ),
    Product(
        productImage = "https://example.com/images/headphones.jpg",
        productName = "Wireless Headphones",
        ratting = 3,
        price = 149.99f
    ),
    Product(
        productImage = "https://example.com/images/smartwatch.jpg",
        productName = "Smartwatch Series 5",
        ratting = 4,
        price = 249.75f
    ),
    Product(
        productImage = "https://example.com/images/tablet.jpg",
        productName = "Tablet Air",
        ratting = 4,
        price = 499.00f
    ),
    Product(
        productImage = "https://example.com/images/camera.jpg",
        productName = "Digital Camera",
        ratting = 5,
        price = 799.99f
    ),
    Product(
        productImage = "https://example.com/images/speaker.jpg",
        productName = "Bluetooth Speaker",
        ratting = 3,
        price = 89.99f
    )
)