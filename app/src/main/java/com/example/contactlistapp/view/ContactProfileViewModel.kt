package com.example.contactlistapp.view


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.contactlistapp.Data.Contact
import java.util.ArrayList

class ContactProfileViewModel : ViewModel() {
    val _contactList = MutableLiveData<ArrayList<Contact>>()
    val contact: LiveData<ArrayList<Contact>> = _contactList



    fun updateContactProfileList(userList: ArrayList<Contact>) {

        _contactList.value = userList
    }


}
