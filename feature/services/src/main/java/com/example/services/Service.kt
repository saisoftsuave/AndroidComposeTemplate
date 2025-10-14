package com.example.services

data class Service(
    val image: String,
    val name: String,
    val rating: Int,
    val price: Float
)

// Sample list of services
val sampleServices = listOf(
    Service(
        image = "https://example.com/images/service1.jpg",
        name = "Service 1",
        rating = 4,
        price = 99.99f
    ),
    Service(
        image = "https://example.com/images/service2.jpg",
        name = "Service 2",
        rating = 5,
        price = 69.50f
    ),
    Service(
        image = "https://example.com/images/service3.jpg",
        name = "Service 3",
        rating = 3,
        price = 149.99f
    ),
    Service(
        image = "https://example.com/images/service4.jpg",
        name = "Service 4",
        rating = 4,
        price = 249.75f
    ),
    Service(
        image = "https://example.com/images/service5.jpg",
        name = "Service 5",
        rating = 4,
        price = 499.00f
    ),
    Service(
        image = "https://example.com/images/service6.jpg",
        name = "Service 6",
        rating = 5,
        price = 799.99f
    ),
    Service(
        image = "https://example.com/images/service7.jpg",
        name = "Service 7",
        rating = 3,
        price = 89.99f
    )
)