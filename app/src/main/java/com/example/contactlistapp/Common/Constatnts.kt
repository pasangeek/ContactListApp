package com.example.contactlistapp.Common

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

object Data_Store{

    const val DataStore_Name = "Contact_Profile"
    val Context.datastore : DataStore<Preferences> by  preferencesDataStore(name = DataStore_Name)


}