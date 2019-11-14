package com.example.myapplication.database.api

import com.example.myapplication.database.model.Hustle

/**
 * Representation of a Hustle that fits with the backend api documentation
 */
object HustleModel {
    /**
     * Representation of a Hustle that we get back from the server
     */
    data class HustleResponse(val userId: String, val properties: HustleResponseProperties)

    /**
     * Hustle Response Properties field
     */
    data class HustleResponseProperties(val hustles: List<Hustle>)

    /**
     * Representation of a Hustle in the request body to the server
     */
    data class HustleRequest(val properties: HustleRequestProperties)

    /**
     * Hustle Request Properties
     */
    data class HustleRequestProperties(val hustle: HustleRequestModel)

    /**
     * Hustle Request Model
     */
    data class HustleRequestModel(
        val providerId: String,
        val category: String,
        val price: Int,
        val description: String,
        val title: String,
        val location: String
    )

    /**
     * Hustle Bid Request Model
     */
    data class HustleBidRequest(val properties: HustleBidRequestProperties)

    /**
     * Hustle Bid Request Properties
     */
    data class HustleBidRequestProperties(val description: String, val bidCost: Int)
}