package com.example.contactlistapp.Data


data class ContactProfileData(
    val name : String,
    val number : String,
    val email : String,
    var  isExpandable : Boolean = false
)