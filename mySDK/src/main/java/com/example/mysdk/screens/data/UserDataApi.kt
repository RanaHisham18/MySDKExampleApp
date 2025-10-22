package com.example.mysdk.screens.data

import retrofit2.Call
import retrofit2.http.GET

interface UserDataApi {
    @GET("users")
    suspend fun getUsers(): List<User>
}

