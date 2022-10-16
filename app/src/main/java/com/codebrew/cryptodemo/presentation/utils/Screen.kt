package com.codebrew.cryptodemo.presentation.utils

sealed class Screen(val route: String) {
    object CryptoEmptyStateScreen: Screen("crypto_empty_state_screen")
    object CryptoValueStateScreen: Screen("crypto_value_state_screen")
    object HomeScreen: Screen("home_screen")
}
