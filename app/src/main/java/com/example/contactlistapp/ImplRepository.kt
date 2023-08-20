package com.example.contactlistapp

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.contactlistapp.Common.Data_Store.datastore
import com.example.contactlistapp.Data.ContactProfileData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ImplRepository(private val context: Context):ContactAbstract {


    companion object{

        val _name = stringPreferencesKey("NAME")
        val _number = stringPreferencesKey("NUMBER")
        val _email = stringPreferencesKey("EMAIL")
    }



    override suspend fun saveContact(contactProfileData: ContactProfileData) {
       context.datastore.edit{ contact ->

           contact[_name] = contactProfileData.name
           contact[_number] = contactProfileData.number
           contact[_email] = contactProfileData.email
       }
    }

    override suspend fun getContact()=context.datastore.data.map { contactProfileData ->

        ContactProfileData(

            name = contactProfileData[_name]!!,
              number = contactProfileData[_number]!!,
                    email = contactProfileData[_email]!!
        )

    }
}