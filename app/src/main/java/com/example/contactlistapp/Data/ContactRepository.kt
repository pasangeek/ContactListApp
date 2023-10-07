package com.example.contactlistapp.Data

import androidx.lifecycle.LiveData

class ContactRepository(private val contactDao: ContactDao) {

    val readAllData: LiveData<List<Contact>> = contactDao.readAllData()
    suspend fun updateContact(contact: Contact){

        contactDao.updateContact(contact)
    }
    suspend fun deleteContact(contact: Contact) {
        contactDao.deleteContact(contact)
    }


    suspend fun addContact(contact: Contact){
        contactDao.addContact(contact)
    }
    fun searchContacts(query: String): LiveData<List<Contact>> {
        return contactDao.searchContacts("%$query%") // Use '%' to allow partial matching
    }
}