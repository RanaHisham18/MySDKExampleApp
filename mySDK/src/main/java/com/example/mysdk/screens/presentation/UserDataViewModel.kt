package com.example.mysdk.screens.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mysdk.network.userApiService
import com.example.mysdk.screens.data.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserDataViewModel : ViewModel() {
    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users

    // Map domain model to UI model StateFlow derived from _users
    private val _userUiModels = _users
        .map { userList -> userList.map { it.toUiModel() } }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val userUiModels: StateFlow<List<UserUiModel>> = _userUiModels

    fun fetchUsers() {
        viewModelScope.launch {
            try {
                val userList = userApiService.getUsers() // suspend function
                _users.value = userList as List<User>
            } catch (e: Exception) {
                // handle error appropriately
            }
        }
    }
}