package com.example.unittestdemo

sealed class Resource<T> {

    data class Success<T>(val data: T) : Resource<T>()

    data class Error<T>(val errorCode: String, val errorMessage: String) : Resource<T>()

    data class NetworkError<T>(val errorMessage: String) : Resource<T>()

    class Undefined<T> : Resource<T>()
}