package com.example.contactlistapp.view.add

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.contactlistapp.Data.Contact
import com.example.contactlistapp.Data.ContactProfileViewModel
import com.example.contactlistapp.R
import com.example.contactlistapp.databinding.AddnewcontactBinding
import com.example.contactlistapp.databinding.FragmentContactListBinding


class AddContactFragment : Fragment() {
    private lateinit var _binding : AddnewcontactBinding
private lateinit var viewModel: ContactProfileViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       _binding = AddnewcontactBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(ContactProfileViewModel::class.java)
        _binding.lifecycleOwner = this
        val view = _binding.root

        _binding.btAdd.setOnClickListener{
            insertDataToTable()

        }
        return  view

    }

private fun insertDataToTable(){
    val name = _binding.etName.text.toString()
    val number = _binding.etNumber.text.toString()
    val email = _binding.etEmail.text.toString()



    if (inputCheck(name, number, email)) {

        val contact = Contact(0, name, number, email)
        viewModel.addUser(contact)
        Toast.makeText(requireContext(), "Contact Added Successfully", Toast.LENGTH_LONG).show()

        findNavController().navigate(R.id.action_addContactFragment_to_contactListFragment)
    }else{

        Toast.makeText(requireContext(), "Please Fill all fields", Toast.LENGTH_LONG).show()
    }
    }


    private fun inputCheck(name:String,number:String,email:String):Boolean{
        return !(TextUtils.isEmpty(name)&&TextUtils.isEmpty(number)&&email.isEmpty())
    }
}