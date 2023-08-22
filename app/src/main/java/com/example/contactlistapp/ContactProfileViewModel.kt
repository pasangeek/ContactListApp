package com.example.contactlistapp


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.contactlistapp.Data.ContactProfileData
import com.example.contactlistapp.Common.Result
import java.util.ArrayList

class ContactProfileViewModel : ViewModel() {
    private val _contactProfileDataList = MutableLiveData<ArrayList<ContactProfileData>>()
    val contactProfileData: LiveData<ArrayList<ContactProfileData>> = _contactProfileDataList

   // val name: MutableLiveData<String> = MutableLiveData("")
   // val number: MutableLiveData<String> = MutableLiveData("")
   // val email: MutableLiveData<String> = MutableLiveData("")


    fun updateContactProfileList(userList: ArrayList<ContactProfileData>) {

        _contactProfileDataList.value = userList
    }

    fun deleteContacts(deletedContacts: ArrayList<ContactProfileData>) {
        _contactProfileDataList.value = deletedContacts
    }


}
