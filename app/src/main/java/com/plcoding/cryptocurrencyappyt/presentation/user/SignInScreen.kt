package com.plcoding.cryptocurrencyappyt.presentation.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.plcoding.cryptocurrencyappyt.presentation.Screen
import com.plcoding.cryptocurrencyappyt.presentation.coin_list.CoinListViewModel
import com.plcoding.cryptocurrencyappyt.presentation.user.components.InputType
import com.plcoding.cryptocurrencyappyt.presentation.user.components.TextInput

@Composable
fun SignInScreen(
    navController: NavController,
    viewModel: CoinListViewModel = hiltViewModel(),
    onLogin: (String, String) -> Unit
){
    val passwordFocusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Column(
        Modifier
            .background(Color.Black)
            .padding(24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.Bottom),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var userName by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        Text(
            text = "SIGN IN",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Green,
            modifier = Modifier.padding(bottom = 150.dp)
        )

        TextInput(
            inputType = InputType.UserName,
            onValueChanged = { userName = it },
            keyboardActions = KeyboardActions(onNext = {
                passwordFocusRequester.requestFocus()
            })
        )
        TextInput(
            inputType = InputType.Password,
            onValueChanged = { password = it },
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
                onLogin(userName, password)
            }),
            focusRequester = passwordFocusRequester
        )
        Button(
            onClick = {
                focusManager.clearFocus()
                onLogin(userName, password)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("SIGN IN", Modifier.padding(vertical = 8.dp))
        }
        Divider(
            color = Color.White.copy(alpha = 0.3f),
            thickness = 1.dp,
            modifier = Modifier.padding(top = 48.dp)
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Don't have an account?", color = Color.White)
            TextButton(onClick = {
                navController.navigate(Screen.SignUpScreen.route)
            }) {
                Text("SIGN UP")
            }
        }
    }

}