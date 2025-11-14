package com.example.myapplication.ui.layouts

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun AppNavigation() {
    val navController = rememberNavController() // 1. Создание NavController
    NavHost(navController = navController, startDestination = "screen1") { // 2. Определение NavHost
        composable("screen1") { // 3. Описание первого экрана
            Screen1(onNavigateToScreen2 = { navController.navigate("screen2") })
        }
        composable("screen2") { // 4. Описание второго экрана
            Screen2(onNavigateBack = { navController.popBackStack() })
        }
    }
}

@Composable
fun Screen1(onNavigateToScreen2: () -> Unit) {
    Button(onClick = onNavigateToScreen2) { // 5. Переход по клику
        Text("Перейти на Экран 2")
    }
}

@Composable
fun Screen2(onNavigateBack: () -> Unit) {
    Button(onClick = onNavigateBack) { // 6. Возврат на предыдущий экран
        Text("Назад")
    }
}