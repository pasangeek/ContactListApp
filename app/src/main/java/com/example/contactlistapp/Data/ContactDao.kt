package com.example.contactlistapp.Data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addContact(contact: Contact)

    @Update
    suspend fun updateContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)



    @Query("SELECT * FROM contact_profile WHERE name LIKE :query OR number LIKE :query OR email LIKE :query")
    fun searchContacts(query: String): LiveData<List<Contact>>
    @Query("SELECT * FROM contact_profile ORDER BY id ASC")
    fun readAllData(): LiveData<List<Contact>>

}