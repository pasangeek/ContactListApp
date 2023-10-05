package com.example.contactlistapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactlistapp.Data.ContactProfileData
import com.example.contactlistapp.databinding.ActivityMainBinding
import com.example.contactlistapp.databinding.AddnewcontactBinding
import com.example.contactlistapp.view.ContactProfileAdapter
import com.example.contactlistapp.view.ContactProfileViewModel
import java.util.Locale

class MainActivity : AppCompatActivity() {
    // Declare view binding, view model, and adapter
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: ContactProfileViewModel
    lateinit var adapter: ContactProfileAdapter
    lateinit var userList: ArrayList<ContactProfileData>

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate the layout using data binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
// Initialize the user list
        userList = ArrayList()

// Initialize the ViewModel using ViewModelProvider

        viewModel = ViewModelProvider(this)[ContactProfileViewModel::class.java]
        binding.vm = viewModel
        binding.lifecycleOwner = this


        // Set up the search functionality using the SearchView
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filter(newText ?: "")
                return true
            }
        })


        // Set up the "Add New Contact" button click listene
        binding.btAddNewContact.setOnClickListener {
            // Inflate the dialog layout using data binding
            onAddNewContactButtonClick()

        }

        fun onAddNewContactButtonClick(){



        }
        // Initialize the ViewModel using ViewModelProvider
        viewModel = ViewModelProvider(this)[ContactProfileViewModel::class.java]

        // Set up RecyclerView and adapter
        binding.contactsRecyclerView.setHasFixedSize(true)
        binding.contactsRecyclerView.layoutManager = LinearLayoutManager(this)
// Initialize the adapter
        adapter = ContactProfileAdapter(userList, viewModel)
        binding.contactsRecyclerView.adapter = adapter


        // Observe changes in the contact profile data using LiveData
        viewModel.contactProfileData.observe(this)

        { contactsProfileData ->
            // Update the contact list in the adapter
            adapter.contactProfileData = contactsProfileData
            adapter.notifyDataSetChanged()


        }
        // Update the contact profile list in the ViewModel
        viewModel.updateContactProfileList(userList)


    }

    fun onAddNewContactButtonClick() {
        val inflater = LayoutInflater.from(this)


        var dialogBinding: AddnewcontactBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.addnewcontact,
            null,
            false
        )
        dialogBinding.vm = viewModel
        dialogBinding.lifecycleOwner = this

        val nameBB = dialogBinding.etName
        val emailBB = dialogBinding.etEmail
        val numberBB = dialogBinding.etNumber

        // Show an AlertDialog for adding new contact
        AlertDialog.Builder(this)
            .setView(dialogBinding.root)
            .setPositiveButton("OK") { dialog, _ ->
                val name = nameBB.text.toString()
                val number = numberBB.text.toString()
                val email = emailBB.text.toString()
                val missingFields = mutableListOf<String>()

                if (name.isEmpty()) {
                    missingFields.add("Name")
                }

                if (number.isEmpty()) {
                    missingFields.add("Number")
                }

                if (email.isEmpty()) {
                    missingFields.add("Email")
                }

                if (missingFields.isEmpty()) {
                    userList.add(
                        ContactProfileData(

                            " $name",
                            " $number","$email"



                        )
                    )
                    adapter.notifyDataSetChanged()
                    Toast.makeText(this, getString(R.string.toast_adding_user_success), Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                    Toast.makeText(this, getString(R.string.toast_adding_contact), Toast.LENGTH_LONG).show()
                } else {
                    val missingFieldsMessage = missingFields.joinToString(", ")
                    val errorMessage = "Please fill in the following fields: $missingFieldsMessage"
                    Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
                }
            }.setNegativeButton("Cancel") { _, _ ->
                Toast.makeText(this, getString(R.string.toast_cancel), Toast.LENGTH_LONG).show()
            }
            .create().show()

    }

    // Filter the list based on the search query
    private fun filter(query: String) {
        val filteredList = java.util.ArrayList<ContactProfileData>()
        for (i in userList) {
            if (i.name?.lowercase(Locale.ROOT)?.contains(query)!!) {
                filteredList.add(i)
            }
        }
// Update the adapter with the filtered list or show a message
        if (filteredList.isEmpty()) {
            Toast.makeText(this, getString(R.string.toast_no_data_found), Toast.LENGTH_SHORT).show()
        } else {
            adapter.setFilteredList(filteredList)
        }
    }


}