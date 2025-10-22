package com.example.mysdk.integration

import android.content.Context
import android.content.Intent
import com.example.mysdk.screens.presentation.MainActivity

object UserScreenLauncher {
    fun openUserScreen(context: Context) {
        val intent = Intent(context, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }
}
