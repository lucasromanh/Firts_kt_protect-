package com.saltacoders.compararprecios

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.saltacoders.compararprecios.screens.Principal
import com.saltacoders.compararprecios.ui.theme.CompararPreciosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen()
        setContent {
            CompararPreciosTheme {
                Box {
                    Principal()
                }
            }
        }
    }
}
