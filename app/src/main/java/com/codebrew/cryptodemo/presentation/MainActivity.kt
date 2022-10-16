package com.codebrew.cryptodemo.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.codebrew.cryptodemo.presentation.screens.empty_state_detail.CryptoEmptyStateScreen
import com.codebrew.cryptodemo.presentation.screens.home.HomeScreen
import com.codebrew.cryptodemo.presentation.ui.theme.CryptoDemoTheme
import com.codebrew.cryptodemo.presentation.utils.Screen
import com.codebrew.cryptodemo.presentation.screens.value_state_detail.CryptoValueStateScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoDemoTheme {

                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.HomeScreen.route
                    ) {
                        composable(
                            route = Screen.HomeScreen.route
                        ) {
                            HomeScreen(navController)
                        }
                        composable(
                            route = Screen.CryptoValueStateScreen.route
                        ) {
                            CryptoValueStateScreen()
                        }
                        composable(
                            route = Screen.CryptoEmptyStateScreen.route
                        ) {
                            CryptoEmptyStateScreen()
                        }
                    }

                }

            }
        }
    }
}
