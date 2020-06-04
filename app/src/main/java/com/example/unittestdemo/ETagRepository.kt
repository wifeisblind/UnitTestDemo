package com.example.unittestdemo

interface ETagRepository {
    suspend fun getCommonCars(): Resource<CommonCar>
}
