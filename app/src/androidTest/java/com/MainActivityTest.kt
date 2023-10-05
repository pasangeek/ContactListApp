package com

import android.text.Editable
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.contactlistapp.Data.Contact

import com.example.contactlistapp.MainActivity
import com.example.contactlistapp.view.ContactProfileAdapter
import com.example.contactlistapp.Data.ContactProfileViewModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


@RunWith(androidx.test.ext.junit.runners.AndroidJUnit4::class)
class MainActivityTest {



        @Mock
        private lateinit var mockViewModel: ContactProfileViewModel

        private lateinit var mainActivity: MainActivity

        @Before
        fun setUp() {
            MockitoAnnotations.initMocks(this)
            mainActivity = MainActivity()
            mainActivity.viewModel = mockViewModel
        }

        @Test
        fun testAddNewContact() {
            // Mock user input
            val name = "John"
            val number = "123"
            val email = "john@example.com"

            // Mock the AlertDialog
            val mockAlertDialog = Mockito.mock(AlertDialog::class.java)

            // Mock EditTexts for name, number, and email
            val mockNameEditText = Mockito.mock(EditText::class.java)
            Mockito.`when`(mockNameEditText.text).thenReturn(Mockito.mock(CharSequence::class.java) as Editable?)
            Mockito.`when`(mockNameEditText.text.toString()).thenReturn(name)

            val mockNumberEditText = Mockito.mock(EditText::class.java)
            Mockito.`when`(mockNumberEditText.text).thenReturn(Mockito.mock(CharSequence::class.java) as Editable?)
            Mockito.`when`(mockNumberEditText.text.toString()).thenReturn(number)

            val mockEmailEditText = Mockito.mock(EditText::class.java)
            Mockito.`when`(mockEmailEditText.text).thenReturn(Mockito.mock(CharSequence::class.java) as Editable?)
            Mockito.`when`(mockEmailEditText.text.toString()).thenReturn(email)

            // Mock the AlertDialog's positive button click


            // Mock the AlertDialog.Builder
            var mockBuilder = Mockito.mock(AlertDialog.Builder::class.java)
            Mockito.`when`(mockBuilder.setView(Mockito.any())).thenReturn(mockBuilder)
            Mockito.`when`(mockBuilder.setPositiveButton(Mockito.anyString(), Mockito.any())).thenReturn(mockBuilder)
            Mockito.`when`(mockBuilder.setNegativeButton(Mockito.anyString(), Mockito.any())).thenReturn(mockBuilder)
            Mockito.`when`(mockBuilder.create()).thenReturn(mockAlertDialog)
            Mockito.`when`(mockBuilder.setPositiveButton(Mockito.anyString(), Mockito.any())).thenAnswer {

                mockBuilder
            }

            // Set up the ViewModel's userListLiveData
            val userListLiveData = MutableLiveData<ArrayList<Contact>>()
            val userList = ArrayList<Contact>()
            Mockito.`when`(mockViewModel.contact).thenReturn(userListLiveData)
            userListLiveData.value = userList

            // Set up the RecyclerView and adapter
            val mockRecyclerView = Mockito.mock(RecyclerView::class.java)
            val mockAdapter = Mockito.mock(ContactProfileAdapter::class.java)
            mainActivity.binding.contactsRecyclerView = mockRecyclerView
            Mockito.`when`(mainActivity.binding.contactsRecyclerView.adapter).thenReturn(mockAdapter)

            // Trigger the "Add New Contact" button click
            mainActivity.onAddNewContactButtonClick()

            // Verify that the AlertDialog is shown
            Mockito.verify(mockBuilder).create()

            // Verify that the contact is added to the user list
            Mockito.verify(mockViewModel).updateContactProfileList(Mockito.any())
            Mockito.verify(mockAdapter).notifyDataSetChanged()
        }
    }