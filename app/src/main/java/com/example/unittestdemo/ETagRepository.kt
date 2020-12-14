package com.example.unittestdemo

interface ETagRepository {
    suspend fun deposit(editInfo: EditCarInfo): Resource<Unit>
}
