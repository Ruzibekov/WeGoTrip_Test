package com.ruzibekov.wegotrip_test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ruzibekov.presentation.screens.main.MainScreen
import com.ruzibekov.presentation.theme.WeGoTrip_TestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeGoTrip_TestTheme {
                MainScreen()
            }
        }
    }
}