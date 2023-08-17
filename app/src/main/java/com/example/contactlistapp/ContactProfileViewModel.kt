package com.example.contactlistapp


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.contactlistapp.Data.ContactProfileData
import com.example.contactlistapp.Common.Result

class ContactProfileViewModel : ViewModel() {
    private val _contactProfileDataList = MutableLiveData<List<ContactProfileData>>()
    val contactProfileData: LiveData<List<ContactProfileData>> = _contactProfileDataList

   // val name: MutableLiveData<String> = MutableLiveData("")
   // val number: MutableLiveData<String> = MutableLiveData("")
   // val email: MutableLiveData<String> = MutableLiveData("")


    fun updateContactProfileList(newList: List<ContactProfileData>) {

        _contactProfileDataList.value = newList
    }

}
