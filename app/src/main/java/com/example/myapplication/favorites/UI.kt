package com.example.myapplication.favorites

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myapplication.di.MyHiltViewModel
import com.example.myapplication.ui.layouts.GradientButton
import com.example.myapplication.ui.model.ItemModel

@Composable
fun Favorite(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround, // Distributes space evenly around items
        horizontalAlignment = Alignment.CenterHorizontally // Centers items horizontally
    ) {
        Text("Album Screen")
        FavoritesUI()
    }
}

@Composable
fun FavoritesUI(
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
        ItemModel("Элемент", 3),
        ItemModel("Элемент", 4),
        ItemModel("Элемент", 5),
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