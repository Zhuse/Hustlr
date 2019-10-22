package com.example.myapplication.database.api

import com.example.myapplication.database.model.Hustle

object HustleModel {
    data class HustleResponse(val userId: String, val properties: Properties)
    data class Properties(val hustles: List<Hustle>)
}