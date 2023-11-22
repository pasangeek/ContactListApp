package com.example.contactlistapp.view.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactlistapp.Data.Contact
import com.example.contactlistapp.Data.ContactProfileViewModel
import com.example.contactlistapp.R
import com.example.contactlistapp.databinding.FragmentContactListBinding
import com.example.contactlistapp.view.ContactProfileAdapter


class ContactListFragment : Fragment() {


    private var _binding: FragmentContactListBinding? = null

    private lateinit var viewModel: ContactProfileViewModel
    private lateinit var adapter: ContactProfileAdapter
    private var userList: List<Contact> = emptyList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentContactListBinding.inflate(inflater, container, false)
        val view = _binding!!.root
        viewModel = ViewModelProvider(this).get(ContactProfileViewModel::class.java)
        adapter = ContactProfileAdapter(userList, viewModel)
        _binding!!.recyclerView.adapter = adapter
        _binding!!.recyclerView.layoutManager = LinearLayoutManager(requireContext())


        viewModel.readAllData.observe(viewLifecycleOwner, Observer { contactList ->
            // Update your UI with the new list of contacts here
            // For example, you can use contactList to populate a RecyclerView or any other UI components.
            // Update the contact list in the adapter
            userList = contactList
            adapter.contactProfileData = userList
            adapter.notifyDataSetChanged()
        })
        _binding!!.floatingActionButton.setOnClickListener {

            findNavController().navigate(R.id.action_contactListFragment_to_addContactFragment)
        }
     _binding!!.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
         override fun onQueryTextSubmit(query: String?): Boolean {
             // Handle search query submission if needed
             return false
         }

         override fun onQueryTextChange(newText: String?): Boolean {
             // Update the contact list based on the search query
             viewModel.searchContacts(newText.orEmpty()).observe(viewLifecycleOwner, Observer { contactList ->
                 adapter.submitContactList(contactList)
             })
             return true
         }
     })
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Clearing the binding reference
        _binding = null

    }

}