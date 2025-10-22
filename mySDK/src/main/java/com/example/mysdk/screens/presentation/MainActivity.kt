package com.example.mysdk.screens.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight.Companion.W800
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mysdk.screens.presentation.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    private val viewModel: UserDataViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UserListScreen(
                        modifier = Modifier.padding(innerPadding),
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}

@Composable
fun UserListScreen(modifier: Modifier = Modifier, viewModel: UserDataViewModel) {
    LaunchedEffect(Unit) { viewModel.fetchUsers() }

    val usersList by viewModel.userUiModels.collectAsState()

    Column(modifier = modifier.padding(24.dp)) {
        Text(text = "Users", fontSize = 14.sp, fontWeight = W800)
        Spacer(modifier = Modifier.height(8.dp))
        UserList(usersList = usersList)
    }
}


@Composable
fun UserList(usersList: List<UserUiModel>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.padding(8.dp)) {
        items(usersList.size) { index ->
            UserInfoCard(user = usersList[index])
        }
    }
}

@Composable
fun UserInfoCard(user: UserUiModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(12.dp)
            ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            Text(text = user.fullName, fontSize = 14.sp, fontWeight = W800)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = user.email, fontSize = 12.sp)
        }
    }
}
