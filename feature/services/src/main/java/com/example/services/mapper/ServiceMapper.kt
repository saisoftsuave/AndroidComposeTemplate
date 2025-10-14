package com.example.services.mapper

import com.example.network.services.model.ServiceDto

fun ServiceDto.toService(): com.example.services.Service {
    return com.example.services.Service(
        image = this.image ?: "",
        name = this.name ?: "",
        rating = this.rating ?: 0,
        price = this.price ?: 0f
    )
}