package com.example.unittestdemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ETagViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        @Suppress("UNCHECKED_CAST")
        return when(modelClass) {
//            ETagPage1ViewModel::class.java -> ETagPage1ViewModel(repo)
            else -> throw RuntimeException("No Such ViewModel")
        } as T
    }
}