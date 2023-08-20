package com.example.contactlistapp

import com.example.contactlistapp.Data.ContactProfileData
import kotlinx.coroutines.flow.Flow

interface ContactAbstract {

    suspend fun saveContact(contactProfileData: ContactProfileData )
    suspend fun getContact(): Flow<ContactProfileData>

}