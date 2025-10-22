package com.example.mysdk.network

import com.example.mysdk.screens.data.UserDataApi
import kotlin.jvm.java
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Header interceptor to add custom headers
val headerInterceptor = Interceptor { chain ->
    val originalRequest: Request = chain.request()
    val requestWithHeaders = originalRequest.newBuilder()
//        .header("Authorization", "Bearer your_token_here")
//        .header("Accept", "application/json")
        .build()
    chain.proceed(requestWithHeaders)
}

// OkHttpClient with interceptor
val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(headerInterceptor)
    .build()

// Retrofit builder
val retrofit = Retrofit.Builder()
    .baseUrl("https://jsonplaceholder.typicode.com/")
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

// Create API service instance
val userApiService = retrofit.create(UserDataApi::class.java)
