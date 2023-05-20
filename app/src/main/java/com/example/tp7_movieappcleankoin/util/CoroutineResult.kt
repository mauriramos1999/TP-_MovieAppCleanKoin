package com.example.tp7_movieappcleankoin.util

sealed class CoroutineResult<out T : Any> {
    class Success<out T : Any>(val data: T) : CoroutineResult<T>()
    class Failure(val exception: Exception) : CoroutineResult<Nothing>()
}