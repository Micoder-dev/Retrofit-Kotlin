package com.micoder.retrofit_kotlin.api

import com.micoder.retrofit_kotlin.model.Users
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("users")
    suspend fun getUsers() : Response<Users>

}