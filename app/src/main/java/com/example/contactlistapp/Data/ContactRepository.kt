package com.example.contactlistapp.Data

import androidx.lifecycle.LiveData

class ContactRepository(private val contactDao: ContactDao) {

    val readAllData: LiveData<List<Contact>> = contactDao.readAllData()

    suspend fun addContact(contact: Contact){
        contactDao.addContact(contact)
    }

}