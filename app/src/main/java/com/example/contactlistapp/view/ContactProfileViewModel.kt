package com.example.contactlistapp.view


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.contactlistapp.Data.ContactProfileData
import com.example.contactlistapp.Common.Result
import java.util.ArrayList

class ContactProfileViewModel : ViewModel() {
    val _contactProfileDataList = MutableLiveData<ArrayList<ContactProfileData>>()
    val contactProfileData: LiveData<ArrayList<ContactProfileData>> = _contactProfileDataList



    fun updateContactProfileList(userList: ArrayList<ContactProfileData>) {

        _contactProfileDataList.value = userList
    }


}
