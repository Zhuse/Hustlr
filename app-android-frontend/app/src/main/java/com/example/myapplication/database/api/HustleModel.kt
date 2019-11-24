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
     * Hustle Patch Response
     */
    data class HustlePatchResponse(val userId: String, val properties: HustlePatchResponseProperties)

    /**
     * Hustle Patch Response Properties
     */
    data class HustlePatchResponseProperties(val hustle: Hustle)

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
     * Representation of a Hustle in the PATCH request body
     */
    data class HustlePatchRequest(val properties: HustlePatchRequestProperties)

    /**
     * HustlePatchRequestProperties
     */
    data class HustlePatchRequestProperties(val hustle: HustlePatchRequestModel)

    /**
     * HustlePatchRequestModel
     */
    data class HustlePatchRequestModel(val hustlrId: String, val status: String)

    /**
     * Hustle Bid Request Model
     */
    data class HustleBidRequest(val properties: HustleBidRequestProperties)

    /**
     * Hustle Bid Request Properties
     */
    data class HustleBidRequestProperties(val description: String, val bidCost: Int)
}