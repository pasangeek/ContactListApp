package com.example.contactlistapp.Data


data class ContactProfileData(
    var name : String,
    var number : String,
    var email : String,
    var  isExpandable : Boolean = false
)