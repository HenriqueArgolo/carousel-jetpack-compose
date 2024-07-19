package com.example.carrosseljc.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.carrosseljc.ui.theme.CarrosselJCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CarrosselJCTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   val imageList = listOf(
                        "https://picsum.photos/id/260/500/800",
                        "https://picsum.photos/id/249/500/800",
                        "https://picsum.photos/id/254/500/800",
                        "https://picsum.photos/id/265/500/800",
                        "https://picsum.photos/id/276/500/800",
                        "https://picsum.photos/id/256/500/800",

                        )

                    CarrosselCard(imageList)
                }
            }
        }
    }
}

