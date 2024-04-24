package com.plcoding.cryptocurrencyappyt.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.InvalidationTracker
import com.plcoding.cryptocurrencyappyt.data.User
import com.plcoding.cryptocurrencyappyt.data.UserViewModel
import com.plcoding.cryptocurrencyappyt.presentation.coin_detail.CoinDetailScreen
import com.plcoding.cryptocurrencyappyt.presentation.coin_list.CoinListScreen
import com.plcoding.cryptocurrencyappyt.presentation.ui.theme.CryptocurrencyAppYTTheme
import com.plcoding.cryptocurrencyappyt.presentation.user.SignInScreen
import com.plcoding.cryptocurrencyappyt.presentation.user.SignUpScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptocurrencyAppYTTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    lateinit var myUserViewModel:UserViewModel
                    NavHost(
                        navController = navController,
                        startDestination = Screen.SignInScreen.route
                    ) {
                        composable(
                            route = Screen.SignInScreen.route
                        ) {
                            SignInScreen(
                                navController,
                                onLogin = {username, password ->
                                    myUserViewModel = ViewModelProvider(this@MainActivity).get(UserViewModel::class.java)
                                    val userList= myUserViewModel.readAllData
                                        .observe(this@MainActivity, Observer { users->
                                            if(users!=null){
                                                for(user in users){
                                                    if ( username.trim()== user.UserName.trim() && password.trim() == user.Password.trim()){
                                                        navController.navigate(Screen.CoinListScreen.route)
                                                        break
                                                    }
                                                }
                                            }
                                        })
                                }
                            )
                        }
                        composable(
                            route = Screen.SignUpScreen.route
                        ) {
                            SignUpScreen(
                                navController,
                                onSignUp = {name,phone,email,address,username,password->
                                    myUserViewModel = ViewModelProvider(this@MainActivity).get(UserViewModel::class.java)
                                    val user = User(0, name, phone, address, username, password)
                                    myUserViewModel.addUser(user)
                                    navController.navigate(Screen.SignInScreen.route)
                                }
                            )
                        }
                        composable(
                            route = Screen.CoinListScreen.route
                        ) {
                            CoinListScreen(navController)
                        }
                        composable(
                            route = Screen.CoinDetailScreen.route + "/{coinId}"
                        ) {
                            CoinDetailScreen()
                        }
                    }
                }
            }
        }
    }
}
