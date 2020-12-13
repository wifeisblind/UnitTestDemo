package com.example.unittestdemo

class CheckInputNumber {

    fun isNumberValid(number: Int): Boolean {
        return number in 0..99
    }
}