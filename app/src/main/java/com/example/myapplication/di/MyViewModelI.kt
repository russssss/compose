package com.example.myapplication.di

import kotlinx.coroutines.flow.StateFlow

interface MyViewModelI {
    fun showData()
    fun dataHot(): StateFlow<Int>
    fun updateData()
}