package com.example.mysdk.screens.presentation

import com.example.mysdk.screens.data.User



data class UserUiModel(
    val id: Int,
    val fullName: String,
    val username: String,
    val email: String, )

// Mappers

fun User.toUiModel(): UserUiModel {
    return UserUiModel(
        id = id,
        fullName = name,
        username = username,
        email = email
    )
}
