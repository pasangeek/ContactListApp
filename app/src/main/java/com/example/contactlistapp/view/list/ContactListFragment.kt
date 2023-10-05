package com.example.contactlistapp.view.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.contactlistapp.R
import com.example.contactlistapp.databinding.FragmentContactListBinding


class ContactListFragment : Fragment() {


private lateinit var _binding : FragmentContactListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentContactListBinding.inflate(inflater, container, false)
        val view = _binding.root
_binding.floatingActionButton.setOnClickListener{

    findNavController().navigate(R.id.action_contactListFragment_to_addContactFragment)
}
return view
    }


}