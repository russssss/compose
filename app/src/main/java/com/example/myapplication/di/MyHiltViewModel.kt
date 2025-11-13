package com.example.myapplication.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyHiltViewModel @Inject constructor(
) : ViewModel() {

    private val random = kotlin.random.Random
    private val _data = MutableStateFlow(1)

    fun dataHot() =  _data.asStateFlow()

    fun search() {
        viewModelScope.launch {
            delay(1000L) // Пауза в миллисекундах
            _data.value = random.nextInt(1, 1111)
        }
    }
    // Здесь вы можете использовать зависимости
}