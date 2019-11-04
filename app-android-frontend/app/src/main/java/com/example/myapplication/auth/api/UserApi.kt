package com.example.myapplication.auth.api

import com.example.myapplication.BASE_URL
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header

/**
 * Retrofit interface for UserApi Requests.
 */
interface UserApi {

    /**
     * Exchange OAuth tokens with userId.
     *
     * @param accessToken
     * @param idToken
     */
    @GET("user/signOn")
    fun getUser(@Header("AccessToken") accessToken: String, @Header("IdToken") idToken: String)
            : Single<Model.UserResponse>

    companion object {
        /**
         * Create implementation of UserApi endpoints.
         */
        fun create(): UserApi {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(UserApi::class.java)
        }
    }
}