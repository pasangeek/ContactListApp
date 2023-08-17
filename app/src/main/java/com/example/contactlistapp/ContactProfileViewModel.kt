package com.example.contactlistapp


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.contactlistapp.Data.ContactProfileData


class ContactProfileViewModel : ViewModel() {
    private val _contactProfileDataList = MutableLiveData<List<ContactProfileData>>()
    val contactProfileData: LiveData<List<ContactProfileData>> get() = _contactProfileDataList

   // val name: MutableLiveData<String> = MutableLiveData("")
   // val number: MutableLiveData<String> = MutableLiveData("")
   // val email: MutableLiveData<String> = MutableLiveData("")


    fun updateContactProfileList(newList: List<ContactProfileData>) {
        _contactProfileDataList.value = newList
    }

}
