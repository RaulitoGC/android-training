package com.rguzmanc.simpleapp

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.coroutines.coroutineContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GreetingText("Button")
        }
    }
}

@Composable
fun GreetingText(text: String) {
    Text(
        text = "Hello $text",
        modifier = Modifier
            .width(80.dp)
            .height(60.dp)
            .clickable(onClick = {

            })
            .padding(4.dp),
        style = MaterialTheme.typography.h5,
        fontWeight = FontWeight.Bold

    )
}


@Preview(showBackground = true)
@Composable
fun DefaultPreviewMainActivity() {
    GreetingText("Button")
}