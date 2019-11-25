package com.example.myapplication.database.api

import com.example.myapplication.BASE_URL
import com.example.myapplication.database.model.Hustle
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

/**
 * An interface for Retrofit to make API calls regarding Hustles
 */
interface HustleApi {

    /**
     * Get a list of hustles given a user id
     */
    @GET("hustle/users/{userId}")
//    fun getHustlesByUser(@Path("userId") hustlrId: String) : Call<List<Hustle>>
    fun getHustlesByUser(@Path("userId") hustlrId: String) : Call<HustleModel.HustleResponse>

    /**
     * Get all hustles that a user has been matched with
     */
    @GET("hustle/users/{userId}/matched")
//    fun getHustlesByUserMatched(@Path("userId") hustlrId: String) : Single<HustleModel.HustleResponse>
    fun getHustlesByUserMatched(@Path("userId") hustlrId: String) : Call<HustleModel.HustleResponse>

    /**
     * Post a new hustle
     */
    @POST("hustle/users/{userId}")
    fun postHustle(@Path("userId") providerId: String, @Body hustle: HustleModel.HustleRequest) : Call<HustleModel.HustlePatchResponse>

    /**
     * Update an existing hustle
     */
    @PATCH("hustle/users/{userId}/{hustleId}")
    fun updateHustle(@Path("userId") hustlrId: String, @Path("hustleId") hustleId: String, @Body hustle: HustleModel.HustlePatchRequest) : Call<HustleModel.HustlePatchResponse>

    /**
     * Post a hustle bid
     */
    @POST("hustle/users/{userId}/{hustleId}/bid")
    fun postHustleBid(@Path("userId") userId: String, @Path("hustleId") hustleId: String, @Body hustleBid: HustleModel.HustleBidRequest) : Call<Hustle>

    companion object {
        /**
         * Create a HustleApi object
         */
        fun create(): HustleApi {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(HustleApi::class.java)
        }
    }
}