package com.example.myapplication.shop

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myapplication.R
import com.example.myapplication.di.MyHiltViewModel
import com.example.myapplication.ui.layouts.GradientButton
import com.example.myapplication.ui.model.ItemModel

@Composable
fun Shop(modifier: Modifier = Modifier) {
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
//        MyOutlinedTextField {
//            println("search text ${it}")
//        }
//        MyLazyColumn()
////        Text("Album Screen")
////        MyLazyColumn()
//    }
    Column(
        modifier = Modifier
            .fillMaxSize() // Fills the entire available space
            .background(Color.LightGray), // Background color for visibility
        verticalArrangement = Arrangement.SpaceAround, // Distributes space evenly around items
        horizontalAlignment = Alignment.CenterHorizontally // Centers items horizontally
    ) {
        MyOutlinedTextField {
            println("search text ${it}")
        }
        MyLazyColumn({

        })
    }
}


@Composable
fun MyLazyColumn(
    onSearch: (String) -> Unit,
    searchViewModel: MyHiltViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val random = kotlin.random.Random
    val unreadDestinations by searchViewModel.dataHot().collectAsStateWithLifecycle()

    LaunchedEffect(unreadDestinations) {
        Toast.makeText(context, "Это флоу + ${unreadDestinations}", Toast.LENGTH_SHORT).show()
    }



    val itemsList = remember {
        mutableStateListOf(
            ItemModel("Элемент", 0),
            ItemModel("Элемент", 1),
            ItemModel("Элемент", 2),
            ItemModel("Элемент", 3),
            ItemModel("Элемент", 4),
            ItemModel("Элемент", 5),
        )
    }

   repeat(100){
        itemsList.add(ItemModel("Элемент", 0))
    }

    val listState = rememberLazyListState()

//    LazyColumn(state = listState, modifier = Modifier.fillMaxSize())
    LazyVerticalGrid(
        columns = GridCells.Fixed(2) // Устанавливаем 2 колонки
    ) {
        itemsIndexed(itemsList) { index, item ->
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceAround, // Distributes space evenly around items
                horizontalAlignment = Alignment.CenterHorizontally // Centers items horizontally

            ) {
                Image(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = "Иконка")
                GradientButton(
                    text = "Click Me",
                    gradientColors = listOf(Color.Red, Color.Blue),
                    onClick = {
//                        searchViewModel.search()
                    }
                )
            }

//            Button(onClick = {
//                // Действие при нажатии кнопки
//                Toast.makeText(context, "Кнопка нажата! + ${index}", Toast.LENGTH_SHORT).show()
//                itemsList.set(index, ItemModel("Обновлен ${index} ${random.nextInt(1, 1111)}", 0))
//
//            }) {
//                // Текст на кнопке
//                Text("Нажми меня")
//            }

        }
    }
}

@Composable
fun SearchBar(onSearch: (String) -> Unit) {
    var query by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Поиск",
            modifier = Modifier.padding(end = 8.dp)
        )
//        MyOutlinedTextField()
    }
}

@Composable
fun MyOutlinedTextField(onSearch: (String) -> Unit) {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = {
            text = it
            onSearch(it)
        },
        label = { Text("Enter your name") }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewSearchBar() {
    SearchBar(onSearch = { query -> println("Поиск: $query") })
}