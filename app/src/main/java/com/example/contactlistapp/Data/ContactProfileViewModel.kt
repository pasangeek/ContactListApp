package com.example.contactlistapp.Data


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactlistapp.Data.Contact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.ArrayList

class ContactProfileViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<Contact>>
    private val repository: ContactRepository

    init {
        val contactDao = ContactDatabase.getDatabase(application).userDao()
        repository = ContactRepository(contactDao)
        readAllData = repository.readAllData
    }

    fun addUser(contact: Contact) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addContact(contact)
        }
    }

    fun updateContact(contact: Contact) {

        viewModelScope.launch(Dispatchers.IO) {


            repository.updateContact(contact)
        }

    }

    fun deleteContact(contact: Contact) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteContact(contact)
        }
    }

    fun searchContacts(query: String): LiveData<List<Contact>> {
        return repository.searchContacts(query)
    }
  /* fun searchContacts(query: String): LiveData<List<Contact>> {
       val searchResults: LiveData<List<Contact>> = ContactRepository.searchContacts(query)

       // Create an empty LiveData to handle no results
       val emptyListLiveData: LiveData<List<Contact>> = MutableLiveData(emptyList())

       // Use MediatorLiveData to switch between searchResults and emptyListLiveData
       val resultLiveData = MediatorLiveData<List<Contact>>()

       resultLiveData.addSource(searchResults) { contacts ->
           if (contacts.isEmpty()) {
               resultLiveData.value = emptyList()
           } else {
               resultLiveData.value = contacts
           }
       }

       return resultLiveData
   }*/
}
