package com.bigappfactory.testapp

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.bigappfactory.testapp.presentation.composable.ImageLoader
import com.bigappfactory.testapp.presentation.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {
    AppTheme {
        ImageLoader {
            MainScreen()
        }
    }
}
