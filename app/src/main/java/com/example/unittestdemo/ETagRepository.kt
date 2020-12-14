package com.example.unittestdemo

interface ETagRepository {
    suspend fun deposit(editCarInfo: EditCarInfo): Resource<Unit>
}
