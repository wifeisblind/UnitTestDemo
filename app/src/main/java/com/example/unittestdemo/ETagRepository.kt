package com.example.unittestdemo

interface ETagRepository {
    suspend fun getCommonCars(): Resource<List<CommonCar>>
    suspend fun executeTrade(): Resource<String>
}
