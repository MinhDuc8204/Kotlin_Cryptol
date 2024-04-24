package com.plcoding.cryptocurrencyappyt.presentation

sealed class Screen(val route: String) {
    object SignUpScreen: Screen("sign_up_screen")
    object SignInScreen: Screen("sign_in_screen")
    object CoinListScreen: Screen("coin_list_screen")
    object CoinDetailScreen: Screen("coin_detail_screen")
}
