package com.example.contactlistapp.Common

sealed class Result{
    object Loading : Result()
    data class Success<out R>(val result: R) : Result()
    data class Failure(val error: String) : Result()

}
