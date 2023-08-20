package com.example.contactlistapp.Common

import com.example.contactlistapp.Data.ContactProfileData

sealed class Result<out T>{
    object Loading : Result<Nothing>()
    data class Success<out T>(val data: T) : Result<T>()
    data class Failure(val exception: Throwable) : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Failure -> "Failure[exception=$exception]"
            Loading -> "Loading"
        }

}}

