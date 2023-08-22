package com.example.contactlistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
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
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel:ContactProfileViewModel
    private lateinit var adapter: ContactProfileAdapter

    private lateinit var userList:ArrayList<ContactProfileData>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate the layout using data binding
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        userList = ArrayList()


        viewModel = ViewModelProvider(this)[ContactProfileViewModel::class.java]
        binding.vm= viewModel
        binding.lifecycleOwner = this

binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
    filter(newText ?: "")
        return true
    }
})
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
        .setPositiveButton("OK") {  dialog, _ ->
            val name = nameBB.text.toString()
            val number = numberBB.text.toString()
            val email = emailBB.text.toString()

            //var namevm = ContactProfileData(name,number, email)
            if (name.isNotEmpty() && number.isNotEmpty() && email.isNotEmpty()){
                userList.add(ContactProfileData("Name: $name","Mobile No. : $number",email))
                adapter.notifyDataSetChanged()
                Toast.makeText(this,"Adding User Information Success",Toast.LENGTH_SHORT).show()
                dialog.dismiss()
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

        adapter = ContactProfileAdapter(userList,viewModel )
        binding.contactsRecyclerView.adapter=adapter
        // Observe changes in the contact profile data using LiveData
        viewModel.contactProfileData.observe(this)

        { contactsProfileData ->
            // Update the contact list in the adapter
            adapter.contactProfileData = contactsProfileData
            adapter.notifyDataSetChanged()



        }

        viewModel.updateContactProfileList(userList)




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
 private fun filter(query: String) {
     val filteredList = java.util.ArrayList<ContactProfileData>()
     for (i in userList) {
         if (i.name.lowercase(Locale.ROOT).contains(query)) {
             filteredList.add(i)
         }
     }

     if (filteredList.isEmpty()) {
         Toast.makeText(this, "No Data found", Toast.LENGTH_SHORT).show()
     } else {
         adapter.setFilteredList(filteredList)
     }
 }
}