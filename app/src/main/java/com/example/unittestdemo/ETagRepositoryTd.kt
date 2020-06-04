package com.example.unittestdemo

import com.example.unittestdemo.Resource.Success
import kotlinx.coroutines.delay

class ETagRepositoryTd : ETagRepository {

    var resourceCommonCars: Resource<List<CommonCar>> = Success(emptyList())

    override suspend fun getCommonCars(): Resource<List<CommonCar>> {
        delay(100)
        return resourceCommonCars
    }
}
