package com.example.myapplication.di

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class MyViewModelImpl @Inject constructor(
) : MyViewModelI {

    private val random = kotlin.random.Random

    private val _data = MutableStateFlow(1)

    override fun dataHot() =  _data.asStateFlow()

    override fun showData() {
//        myRepository.getData()
    }

    override fun updateData() {
        _data.value = random.nextInt(1, 1111)
    }
}