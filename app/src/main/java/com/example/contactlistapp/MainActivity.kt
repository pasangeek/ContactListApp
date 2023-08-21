package com.example.contactlistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactlistapp.Data.ContactProfileData
import com.example.contactlistapp.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.example.contactlistapp.Common.Result
import com.example.contactlistapp.Common.gone
import com.example.contactlistapp.Common.show
import com.example.contactlistapp.databinding.AddnewcontactBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel:ContactProfileViewModel
    private lateinit var adapter: ContactProfileAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate the layout using data binding
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        viewModel = ViewModelProvider(this)[ContactProfileViewModel::class.java]
        binding.vm= viewModel
        binding.lifecycleOwner = this


binding.btAddNewContact.setOnClickListener{

    val inflater = LayoutInflater.from(this)
    //val b = inflater.inflate(R.layout.newcontact, null)

    val dialogBinding:AddnewcontactBinding = DataBindingUtil.inflate(
        inflater,
        R.layout.addnewcontact,
        null,
        false)
    dialogBinding.vm = viewModel
    dialogBinding.lifecycleOwner = this

    val nameBB = dialogBinding.etName
    val emailBB = dialogBinding.etEmail
    val numberBB = dialogBinding.etNumber
    AlertDialog.Builder(this)
        .setView(dialogBinding.root)
        .setPositiveButton("OK") { _, _ ->
            var name = nameBB.text.toString()
            var number = numberBB.text.toString()
            var email = emailBB.text.toString()

            //var namevm = ContactProfileData(name,number, email)
            if (name.isNotEmpty() && number.isNotEmpty() && email.isNotEmpty()){

                //viewModel.saveData()
                Toast.makeText(this, "Adding contact", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()

            }
        }.setNegativeButton("Cancel") { _, _ ->
            Toast.makeText(this, "Cancel", Toast.LENGTH_LONG).show()
        }
        .create().show()

}
        // Initialize the ViewModel using ViewModelProvider
        viewModel = ViewModelProvider(this)[ContactProfileViewModel::class.java]
        binding.contactsRecyclerView.setHasFixedSize(true)
        binding.contactsRecyclerView.layoutManager= LinearLayoutManager(this)

        adapter = ContactProfileAdapter(viewModel.contactProfileData.value?: emptyList())
        binding.contactsRecyclerView.adapter=adapter
        // Observe changes in the contact profile data using LiveData
        viewModel.contactProfileData.observe(this)

        { contactsProfileData ->
            // Update the contact list in the adapter
            adapter.contactProfileData = contactsProfileData
            adapter.notifyDataSetChanged()



        }
        // Create a list of new data for testing purposes
        val newDataList = listOf(
            // Sample ContactProfileData objects
            ContactProfileData("James Bond", "077252525", "james@gmail.com"),
            ContactProfileData("John Smith", "087252525", "linda.miller@example.com"),
            ContactProfileData("Jane Doe", "070252525", "jennifer.davis@example.com"),
            ContactProfileData("Michael Johnson", "079252525", "david.johnson@example.com"),
            ContactProfileData("James Bond", "077552525", "james@gmail.com"),
            ContactProfileData("John Smith", "077552525", "emma.taylor@example.com"),
            ContactProfileData("James Bond", "077252525", "james@gmail.com"),
            ContactProfileData("John Smith", "087252525", "linda.miller@example.com"),
            ContactProfileData("Jane Doe", "070252525", "jennifer.davis@example.com"),
            ContactProfileData("Michael Johnson", "079252525", "david.johnson@example.com"),
            ContactProfileData("James Bond", "077552525", "james@gmail.com"),

            ContactProfileData("John Smith", "087252525", "linda.miller@example.com"),
            ContactProfileData("Jane Doe", "070252525", "jennifer.davis@example.com"),
            ContactProfileData("Michael Johnson", "079252525", "david.johnson@example.com"),
            ContactProfileData("James Bond", "077552525", "james@gmail.com"),
            ContactProfileData("James Bond", "077252525", "james@gmail.com"),
            ContactProfileData("John Smith", "087252525", "linda.miller@example.com"),
            ContactProfileData("Jane Doe", "070252525", "jennifer.davis@example.com"),
            ContactProfileData("Michael Johnson", "079252525", "david.johnson@example.com"),
            ContactProfileData("James Bond", "077552525", "james@gmail.com"),
            ContactProfileData("John Smith", "077552525", "emma.taylor@example.com")

            // Add more ContactProfileData objects as needed
        )
        viewModel.updateContactProfileList(newDataList)




    }


 /* private fun initObservers() {

        viewModel.contactProfileData.observe(this){result ->
            when (result) {
               is Result.Loading->{
                    binding.progressBar.show()
                }
                is Result.Success<*>->{
                    binding.progressBar.gone()
                    binding.contactsRecyclerView.adapter = ContactProfileAdapter(result as List<ContactProfileData>)
                }

                is Result.Failure->{
                    binding.progressBar.gone()
                    Snackbar.make(binding.root, result.error, Snackbar.LENGTH_LONG).show()
                }

            }

    }

}*/

}