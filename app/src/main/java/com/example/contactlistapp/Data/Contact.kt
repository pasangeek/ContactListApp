package com.example.contactlistapp.Data



import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "contact_profile")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    val id :Int,
    var name : String?,
    var number : String?,
    var email : String?,
    var  isExpandable : Boolean = false
)