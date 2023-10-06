package com.example.contactlistapp.view.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.contactlistapp.Data.Contact
import com.example.contactlistapp.Data.ContactProfileViewModel
import com.example.contactlistapp.R
import com.example.contactlistapp.databinding.FragmentAddContactBinding
import com.example.contactlistapp.view.ContactProfileAdapter


class AddContactFragment : Fragment() {
    private lateinit var _binding: FragmentAddContactBinding
    private lateinit var viewModel: ContactProfileViewModel
    private lateinit var adapter: ContactProfileAdapter
    private lateinit var userList: ArrayList<Contact>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddContactBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(ContactProfileViewModel::class.java)
        userList = ArrayList()
        val view = _binding.root

        _binding.btAdd.setOnClickListener {
            insertDataToTable()

        }
        return view

    }

    private fun insertDataToTable() {
        val name = _binding.etName.text.toString()
        val number = _binding.etNumber.text.toString()
        val email = _binding.etEmail.text.toString()


        val emptyFields = ArrayList<String>() // List to store names of empty fields

        if (name.isEmpty()) {
            emptyFields.add("Name")
        }
        if (number.isEmpty()) {
            emptyFields.add("Number")
        } else if (number.length != 10 || !number.all { it.isDigit() }) {
            // Check if the number has exactly 10 digits and is numeric
            Toast.makeText(
                requireContext(),
                "Please enter a valid 10-digit numeric number",
                Toast.LENGTH_LONG
            ).show()
            return // Exit the function if the number is invalid
        }
        if (email.isEmpty()) {
            emptyFields.add("Email")
        } else if (!isEmailValid(email)) {
            // Check if the email is in a valid format
            Toast.makeText(
                requireContext(),
                "Please enter a valid email address",
                Toast.LENGTH_LONG
            ).show()
            return // Exit the function if the email is not valid
        }

        if (emptyFields.isEmpty()) {
            val contact = Contact(0, name, number, email)
            viewModel.addUser(contact)
            Toast.makeText(requireContext(), "Contact Added Successfully", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addContactFragment_to_contactListFragment)
        } else {
            val message = "Please fill in the following fields: ${emptyFields.joinToString(", ")}"
            Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
        }
    }

    fun isEmailValid(email: String): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$"
        return email.matches(emailRegex.toRegex())
    }

}