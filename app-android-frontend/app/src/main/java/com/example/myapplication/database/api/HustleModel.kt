package com.example.myapplication.database.api

import com.example.myapplication.database.model.Hustle

object HustleModel {
    data class HustleResponse(val userId: String, val properties: HustleResponseProperties)
    data class HustleResponseProperties(val hustles: List<Hustle>)

    data class HustleRequest(val properties: HustleRequestProperties)
    data class HustleRequestProperties(val hustle: HustleRequestModel)
    data class HustleRequestModel(
        val providerId: String,
        val category: String,
        val price: Int,
        val description: String,
        val title: String,
        val location: String
    )
}