package com.example.mysdk.screens.data

import androidx.annotation.Keep
import kotlinx.serialization.SerialName


@Keep
data class User(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("username") val username: String,
    @SerialName("email") val email: String,
    )



