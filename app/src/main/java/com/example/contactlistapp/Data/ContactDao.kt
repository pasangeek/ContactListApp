package com.example.contactlistapp.Data

import androidx.lifecycle.LiveData
import androidx.room.Dao
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
    @Query("SELECT * FROM contact_profile ORDER BY id ASC")
    fun readAllData(): LiveData<List<Contact>>

}