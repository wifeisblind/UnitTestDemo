package com.example.unittestdemo

data class EditCarInfo(
            var editCar1: String,
            var editCar2: String,
            var editUserId: String
) {
    companion object{
        val EMPTY get() = EditCarInfo("", "", "")
    }
}