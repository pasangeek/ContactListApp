package com.example.contactlistapp.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.contactlistapp.Data.Contact
import com.example.contactlistapp.Data.ContactProfileViewModel
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
internal class ContactProfileViewModelTest {

    // This rule ensures that LiveData operations are performed immediately
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    // Create a mock observer for LiveData
    @Mock
    private lateinit var observer: Observer<ArrayList<Contact>>

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
        // Create a list of Contact
        val contactList = ArrayList<Contact>()
        contactList.add(Contact("John", "123-456-7890", "john@example.com"))

        // Observe the LiveData
        viewModel.contact.observeForever(observer)

        // Call the function to update the list
        viewModel.updateContactProfileList(contactList)

        // Verify that the LiveData was updated and the observer was called
        Mockito.verify(observer).onChanged(contactList)


        // Verify the updated LiveData value directly
        val updatedValue = viewModel.contact.value
        assertEquals(contactList, updatedValue)
    }

    @Test
    fun testUpdateContactProfileListFailure() {
        // Create a list of Contact
        // Create an empty list of Contact to simulate a failure scenario
        val contactList = ArrayList<Contact>()

        // Call the function to update the list with an empty list, causing a failure
        viewModel.updateContactProfileList(contactList)

        // Verify that the LiveData contains an empty list
        val updatedValue = viewModel.contact.value
        assertEquals(contactList, updatedValue)
    }

    //This test ensures that these properties are
    // properly initialized and are not null,
    // which is essential for the proper functioning of the ViewModel.
    // You can expand this test method to include additional assertions
    // or specific checks based on your requirements.
    @Test
    fun testNullValues() {
        // Create an instance of ContactProfileViewModel
        val viewModel = ContactProfileViewModel()

        // Verify that important properties are not null
        assertNotNull(viewModel.contact)
        assertNotNull(viewModel._contactList)

    }

}