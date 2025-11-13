package com.example.myapplication.ui.layouts

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.model.ItemModel
import com.example.myapplication.R
import com.example.myapplication.di.MyHiltViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Composable
fun Welcome() {
    Column {
        ViewA(name = "AAA")
        ViewB(name = "BBB")
        ViewC(name = "CCC")
        SimpleLazyRow()
        MyLazyColumn()
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
fun MyLazyColumn(
    searchViewModel: MyHiltViewModel = hiltViewModel(),
    ) {
    val context = LocalContext.current
    val random = kotlin.random.Random
    val unreadDestinations by searchViewModel.dataHot().collectAsStateWithLifecycle()

    LaunchedEffect(unreadDestinations) {
        Toast.makeText(context, "Это флоу + ${unreadDestinations}", Toast.LENGTH_SHORT).show()
    }

    val itemsList = remember { mutableStateListOf(
        ItemModel("Элемент", 0),
        ItemModel("Элемент", 1),
        ItemModel("Элемент", 2),
    )}

    val listState = rememberLazyListState()

    LazyColumn(state = listState, modifier = Modifier.fillMaxSize()) {
        itemsIndexed(itemsList) { index, item ->
            GradientButton(
                text = "Click Me",
                gradientColors = listOf(Color.Red, Color.Blue),
                onClick = {
                    searchViewModel.search()
                }
            )
            Text(
                text = "${item.name}  ${item.number}",
                modifier = Modifier.padding(16.dp)
            )
            val firstVisibleItemIndex = listState.firstVisibleItemIndex
            val firstVisibleItemScrollOffset = listState.firstVisibleItemScrollOffset

            Button(onClick = {
                // Действие при нажатии кнопки
                Toast.makeText(context, "Кнопка нажата! + ${index}", Toast.LENGTH_SHORT).show()
                itemsList.set(index, ItemModel("Обновлен ${index} ${random.nextInt(1, 1111)}", 0))

            }) {
                 // Текст на кнопке
                Text("Нажми меня")
            }

        }
    }
}

@Composable
fun SimpleLazyRow() {

    val itemsList = remember { mutableStateListOf("Элемент 1", "Элемент 2", "Элемент 3") }

    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(itemsList) { item ->
            // Определение того, как будет отображаться каждый элемент
            Text(
                text = item,
                modifier = Modifier
                    .padding(8.dp)
                    .background(Color.Gray)
                    .padding(16.dp)
            )
            MyLayout()
        }
    }
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
fun MyLayout() {
    Column {
        Text("Привет!")
        Row {
            Image(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = "Иконка")
            Text("Текст рядом с иконкой")
        }
    }
}


