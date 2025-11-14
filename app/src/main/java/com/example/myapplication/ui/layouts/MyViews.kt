package com.example.myapplication.ui.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@Preview(showBackground = true)
@Composable
fun Welcome() {
    Column {
        HorizontalScrollLayout()
        ViewA(name = "AAA")
        ViewB(name = "BBB")
        ViewC(name = "CCC")
    }
}

@Composable
fun ViewA(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun ViewB(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun ViewC(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun GradientButton(
    text: String,
    gradientColors: List<Color>,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    cornerRadius: Int = 16,
    ) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        shape = RoundedCornerShape(cornerRadius.dp),
        contentPadding = ButtonDefaults.ContentPadding
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.linearGradient(colors = gradientColors),
                    shape = RoundedCornerShape(cornerRadius.dp)
                )
                .padding(horizontal = 16.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = text, color = Color.White)
        }
    }
}

@Composable
fun HorizontalScrollLayout() {
    val debugColor: Color = Color.Magenta.copy(alpha = 0.5f)
    val scrollState = rememberScrollState() // Создание состояния прокрутки

    Row(
        modifier = Modifier
            .horizontalScroll(scrollState) // Применение горизонтальной прокрутки
            .background(debugColor)
            .padding(32.dp)
    ) {
        val min = 0.9f
        val max = 0.1f
        val randomFloatInRange = min + (Random.nextFloat() * (max - min))
        val randomColor: Color = Color.Blue.copy(alpha = randomFloatInRange)

        repeat(20) { index ->
            Text("Горизонтальный элемент №$index", modifier = Modifier.padding(8.dp).background(generateRandomColor()))
        }
    }
}

fun generateRandomColor(): Color {
    val rnd = Random.Default
    val red = rnd.nextInt(256) // Generates a random integer from 0 to 255
    val green = rnd.nextInt(256)
    val blue = rnd.nextInt(256)

    // Create an ARGB color (Alpha, Red, Green, Blue). Alpha is set to 255 for full opacity.


    return Color(red, green, blue)
}


