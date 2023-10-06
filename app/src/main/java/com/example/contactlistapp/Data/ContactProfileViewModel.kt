package com.example.contactlistapp.Data


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactlistapp.Data.Contact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.ArrayList

class ContactProfileViewModel(application: Application) : AndroidViewModel(application) {
    /*val _contactList = MutableLiveData<ArrayList<Contact>>()
    val contact: LiveData<ArrayList<Contact>> = _contactList



    fun updateContactProfileList(userList: ArrayList<Contact>) {

        _contactList.value = userList
    }*/

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

    fun deleteContactById(contactId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteContactById(contactId)
        }
    }
}
