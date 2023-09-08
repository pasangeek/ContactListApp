package com.example.contactlistapp.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.contactlistapp.Data.ContactProfileData
import junit.framework.Assert.assertNull
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
internal class ContactProfileViewModelTest{

    // This rule ensures that LiveData operations are performed immediately
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    // Create a mock observer for LiveData
    @Mock
    private lateinit var observer: Observer<ArrayList<ContactProfileData>>

    // Create an instance of ContactProfileViewModel
    private lateinit var viewModel: ContactProfileViewModel

    @Before
    fun setUp() {
        // Initialize Mockito annotations
        MockitoAnnotations.initMocks(this)

        // Create the ViewModel
        viewModel = ContactProfileViewModel()
    }

    @Test
    fun testUpdateContactProfileList() {
        // Create a list of ContactProfileData
        val contactList = ArrayList<ContactProfileData>()
        contactList.add(ContactProfileData("John", "123-456-7890", "john@example.com"))

        // Observe the LiveData
        viewModel.contactProfileData.observeForever(observer)

        // Call the function to update the list
        viewModel.updateContactProfileList(contactList)

        // Verify that the LiveData was updated and the observer was called
        Mockito.verify(observer).onChanged(contactList)


        // Verify the updated LiveData value directly
        val updatedValue = viewModel.contactProfileData.value
        assertEquals(contactList, updatedValue)
    }

    @Test
    fun testUpdateContactProfileListFailure() {
        // Create a list of ContactProfileData
        // Create an empty list of ContactProfileData to simulate a failure scenario
        val contactList = ArrayList<ContactProfileData>()

        // Call the function to update the list with an empty list, causing a failure
        viewModel.updateContactProfileList(contactList)

        // Verify that the LiveData contains an empty list
        val updatedValue = viewModel.contactProfileData.value
        assertEquals(contactList, updatedValue)
    }


}