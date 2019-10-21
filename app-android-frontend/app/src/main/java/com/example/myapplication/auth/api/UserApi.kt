package com.example.myapplication.auth.api

import com.example.myapplication.BASE_URL
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {

    @GET("user/email/{user_email}")
    fun getSession(@Path("user_email") email: String): Single<Model.Session>

    companion object {
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