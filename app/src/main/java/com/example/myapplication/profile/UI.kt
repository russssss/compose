package com.example.myapplication.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.myapplication.R

@Composable
fun ProfileLayout() {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .background(Color.LightGray), // Background color for visibility
        verticalArrangement = Arrangement.SpaceAround, // Distributes space evenly around items
        horizontalAlignment = Alignment.Start // Centers items horizontally

    ) {
        Column(
            modifier = Modifier
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(16.dp) // Apply rounded corners
                ),
            verticalArrangement = Arrangement.SpaceAround, // Distributes space evenly around items
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = stringResource(R.string.y_see),
                style = TextStyle(fontWeight = FontWeight.Bold),
                fontSize = 20.sp,
                modifier = Modifier.padding(20.dp)
            )

            FirstMenu()
        }

        Box(
            modifier = Modifier
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(16.dp) // Apply rounded corners
                )
                .padding(bottom = 20.dp)
        ) { }

        Column(
            modifier = Modifier
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(16.dp) // Apply rounded corners
                )
                .padding(bottom = 20.dp),
            verticalArrangement = Arrangement.SpaceAround, // Distributes space evenly around items
            horizontalAlignment = Alignment.Start
        ) {

            Text(
                "Playlist Screen",
                style = TextStyle(fontWeight = FontWeight.Bold),
                fontSize = 20.sp,
                modifier = Modifier.padding(20.dp)
            )
            SecondMenu()
        }

        Box(
            modifier = Modifier
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(16.dp) // Apply rounded corners
                )
                .padding(bottom = 20.dp)
        ) { }

        Column(
            modifier = Modifier
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(16.dp) // Apply rounded corners
                ),
            verticalArrangement = Arrangement.SpaceAround, // Distributes space evenly around items
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                "Playlist Screen",
                style = TextStyle(fontWeight = FontWeight.Bold),
                fontSize = 20.sp,
                modifier = Modifier.padding(20.dp)
            )
            ThirdMenu()
        }
    }
}

@Composable
fun FirstMenu() {

    val itemsList = remember { mutableStateListOf("Элемент 1", "Элемент 2", "Элемент 3") }

    LazyRow(
        modifier = Modifier
            .background(Color.Transparent) // Optional: for visual demonstration
        ,
        verticalAlignment = Alignment.Top,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(itemsList) { item ->
            Column() {
                Text(
                    text = item,
                    modifier = Modifier
                        .padding(8.dp)
                        .background(Color.Gray)
                        .padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun SecondMenu() {
    val itemsList = remember { mutableStateListOf("Элемент 1", "Элемент 2", "Элемент 3") }

    LazyRow(

        verticalAlignment = Alignment.Top,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(itemsList) { item ->
            Column() {
                MyLayout()
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ThirdMenu() {
    val itemsList = remember {
        mutableStateListOf(
            "Элемент 1",
            "Элемент 2",
            "Элемент 3",
            "Элемент 3",
            "Элемент 3",
            "Элемент 3",
            "Элемент 3"
        )
    }

    LazyRow(

        verticalAlignment = Alignment.Top,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(itemsList) { item ->
            Column() {
                GlideImage(
                    model = "https://media.zenfs.com/en/pethelpful_915/742b8b699baa503955d8e014a1304b07",
                    contentDescription = "Translated description of what the image contains",
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp),
                )
            }
        }
    }
}

@Composable
fun MyLayout() {
    Column {
        Text("Привет!")
        Row {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Иконка"
            )
            Text("Текст рядом с иконкой")
        }
    }
}
