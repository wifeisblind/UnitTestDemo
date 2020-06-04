package com.example.unittestdemo

class DummyRepository : ETagRepository {
    override suspend fun getCommonCars(): Resource<CommonCar> {
        throw NotImplementedError()
    }
}
