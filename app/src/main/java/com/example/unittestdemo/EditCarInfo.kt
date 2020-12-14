package com.example.unittestdemo

data class EditCarInfo(
    var editCar1: String,
    var editCar2: String,
    var editUserId: String,
    var isChecked: Boolean
) {
    companion object{
        val EMPTY get() = EditCarInfo("", "", "", false)
    }
}
