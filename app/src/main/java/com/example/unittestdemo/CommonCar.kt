package com.example.unittestdemo

data class CommonCar(
        val carPlate: String,
        val userId: String,
        val seq: String
) {
    companion object{
        val EMPTY get() = CommonCar("", "", "")
    }
}
