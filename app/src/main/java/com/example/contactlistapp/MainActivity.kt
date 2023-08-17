package com.example.contactlistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactlistapp.Data.ContactProfileData
import com.example.contactlistapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel:ContactProfileViewModel
    private lateinit var adapter: ContactProfileAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[ContactProfileViewModel::class.java]
        binding.contactsRecyclerView.setHasFixedSize(true)
        binding.contactsRecyclerView.layoutManager= LinearLayoutManager(this)

        adapter = ContactProfileAdapter(viewModel.contactProfileData.value?: emptyList())
        binding.contactsRecyclerView.adapter=adapter
        viewModel.contactProfileData.observe(this)

        { contactsProfileData ->
            // Update the contact list in the adapter
            adapter.contactProfileData = contactsProfileData
            adapter.notifyDataSetChanged()

        }
        val newDataList = listOf(
            ContactProfileData("vbkjb","455661","hhujkg"),
            ContactProfileData("vbkjb","455661","hhujkg"),
            ContactProfileData("vbkjb","455661","hhujkg"),
            ContactProfileData("vbkjb","455661","hhujkg")
            // Add more ContactProfileData objects as needed
        )
        viewModel.updateContactProfileList(newDataList)




    }
}